package com.aytbyz.tposdemoapp.presentation.ui.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import com.aytbyz.tposdemoapp.R

@Composable
fun PaymentInfoRow(paymentMethod: String, cardUid: String, productId: String) {
    @DrawableRes val iconRes = when (paymentMethod) {
        "NFC" -> R.drawable.creditcard
        "QR" -> R.drawable.box
        else -> R.drawable.creditcard
    }

    val infoText = when (paymentMethod) {
        "NFC" -> "Kart: $cardUid"
        "QR" -> productId
        else -> ""
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(12.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(text = infoText, color = Color.Gray, fontSize = 12.sp)
    }
}