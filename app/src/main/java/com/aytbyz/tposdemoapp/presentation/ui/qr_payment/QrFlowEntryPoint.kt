package com.aytbyz.tposdemoapp.presentation.ui.qr_payment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun QrFlowEntryPoint(
    onBack: () -> Unit,
    onShowPermissionDenied: () -> Unit = {}
) {
    var showQr by remember { mutableStateOf(false) }

    if (showQr) {
        QrPaymentScreen(onBack = onBack)
    } else {
        QrPermissionGate(
            onPermissionGranted = { showQr = true },
            onPermissionDenied = {
                onBack()
                onShowPermissionDenied()
            }
        )
    }
}