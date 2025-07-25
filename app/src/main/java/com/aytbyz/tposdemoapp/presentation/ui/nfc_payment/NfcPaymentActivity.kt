package com.aytbyz.tposdemoapp.presentation.ui.nfc_payment

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aytbyz.tposdemoapp.presentation.util.manager.NfcManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NfcPaymentActivity : AppCompatActivity() {
    private val viewModel: NfcPaymentViewModel by viewModels()

    private lateinit var nfcService: NfcManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nfcService = NfcManager(this) { uid ->
            viewModel.onCardScanned(uid)
        }

        intent?.let { nfcService.handleIntent(it) }

        setContent {
            NfcPaymentScreen(
                viewModel = viewModel,
                onBack = { finish() }
            )
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        nfcService.handleIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        nfcService.enableForegroundDispatch()
    }

    override fun onPause() {
        super.onPause()
        nfcService.disableForegroundDispatch()
    }
}