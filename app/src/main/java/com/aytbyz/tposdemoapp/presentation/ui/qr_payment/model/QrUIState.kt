package com.aytbyz.tposdemoapp.presentation.ui.qr_payment.model

import com.aytbyz.tposdemoapp.domain.model.product.Product

data class QrUIState(
    val remainingTime: Int = 30,
    val scannedProduct: Product? = null,
    val mqttPublishSuccess: Boolean = false,
    val countdownVisible: Boolean = false
)