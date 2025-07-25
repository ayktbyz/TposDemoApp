package com.aytbyz.tposdemoapp.presentation.util.manager

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Parcelable
import android.util.Log

class NfcManager(
    private val activity: Activity,
    private val onCardDetected: (String) -> Unit
) {
    private val nfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(activity)

    fun enableForegroundDispatch() {
        val intent = Intent(activity, activity.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_MUTABLE)
        val filters = arrayOf(IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED))
        nfcAdapter?.enableForegroundDispatch(activity, pendingIntent, filters, null)

        handleIntent(intent)
    }

    fun disableForegroundDispatch() {
        nfcAdapter?.disableForegroundDispatch(activity)
    }

    fun handleIntent(intent: Intent) {
        if (intent.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            val uid = tag?.id?.joinToString("") { String.format("%02X", it) }

            Log.d("NFC", "tag: $tag")
            Log.d("NFC", "techList: ${tag?.techList?.joinToString()}")
            Log.d("NFC", "UID: $uid")

            if (uid == null) {
                Log.e("NFC", "UID is null — card may be unsupported.")
            }

            uid?.let(onCardDetected)
        }
    }
}