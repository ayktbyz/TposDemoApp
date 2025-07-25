package com.aytbyz.tposdemoapp.presentation.ui.nfc_payment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aytbyz.tposdemoapp.R
import com.aytbyz.tposdemoapp.presentation.ui.components.screen.BaseScreen
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NfcPaymentScreen(
    viewModel: NfcPaymentViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val cardUid by viewModel.cardUid.collectAsState()
    val saleCreateSuccess by viewModel.saleCreateSuccess.collectAsState()

    BaseScreen(
        title = stringResource(id = R.string.nfc_payment_title),
        onBack = onBack
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                if (cardUid == null) {
                    Text(
                        text = stringResource(id = R.string.nfc_payment_prompt),
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.nfc_card_read_success, cardUid ?: ""),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    if (saleCreateSuccess) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "✅",
                                fontSize = 64.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = stringResource(R.string.purchase_success_text),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            if (saleCreateSuccess) {
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = stringResource(R.string.home_button_return))
                }
            }
        }
    }
}