package com.aytbyz.tposdemoapp.presentation.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    onSelectQr: () -> Unit,
    onSelectNfc: () -> Unit,
    onSelectLoyalty: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ödeme Yöntemi Seçin", style = MaterialTheme.typography.headlineSmall)

        Button(onClick = onSelectQr, modifier = Modifier.fillMaxWidth()) {
            Text("QR ile Ödeme")
        }

        Button(onClick = onSelectNfc, modifier = Modifier.fillMaxWidth()) {
            Text("Kredi Kartı (NFC)")
        }

        Button(onClick = onSelectLoyalty, modifier = Modifier.fillMaxWidth()) {
            Text("Sadakat Kartı (NFC)")
        }
    }
}