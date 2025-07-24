package com.aytbyz.tposdemoapp.presentation.ui.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aytbyz.tposdemoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentOptionsBottomSheet(
    onSelectQr: () -> Unit,
    onSelectNfc: () -> Unit,
    onSelectLoyalty: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.choose_payment_method),
                style = MaterialTheme.typography.headlineSmall
            )

            Button(onClick = onSelectQr, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.pay_with_qr))
            }

            Button(onClick = onSelectNfc, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.pay_with_nfc))
            }

            Button(onClick = onSelectLoyalty, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.pay_with_loyalty))
            }
        }
    }
}