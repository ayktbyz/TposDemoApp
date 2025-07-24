package com.aytbyz.tposdemoapp.presentation.ui.qr_payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aytbyz.tposdemoapp.presentation.ui.components.BaseScreen

@Composable
fun QrPaymentScreen(
    onBack: () -> Unit
) {
    BaseScreen(title = "QR ile ödeme", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("QR ile ödeme ekranına hoş geldiniz.")
        }
    }
}