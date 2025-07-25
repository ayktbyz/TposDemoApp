package com.aytbyz.tposdemoapp.presentation.ui.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import com.aytbyz.tposdemoapp.R

@Composable
fun PaymentMethodRow(paymentMethod: String) {
    @DrawableRes val iconRes = when (paymentMethod) {
        "NFC" -> R.drawable.creditcard
        "QR" -> R.drawable.qrcode
        else -> R.drawable.creditcard
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(text = paymentMethod, color = Color.Blue)
    }
}