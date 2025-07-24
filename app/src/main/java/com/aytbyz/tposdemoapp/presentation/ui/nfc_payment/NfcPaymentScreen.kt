package com.aytbyz.tposdemoapp.presentation.ui.nfc_payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aytbyz.tposdemoapp.R
import com.aytbyz.tposdemoapp.presentation.ui.components.BaseScreen

@Composable
fun NfcPaymentScreen(
    onBack: () -> Unit
) {
    BaseScreen(
        title = stringResource(id = R.string.nfc_payment_title),
        onBack = onBack
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.nfc_payment_welcome))
        }
    }
}