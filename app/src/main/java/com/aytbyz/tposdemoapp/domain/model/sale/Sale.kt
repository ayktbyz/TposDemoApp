package com.aytbyz.tposdemoapp.domain.model.sale

data class Sale(
    val id: Int = 0,
    val productId: Int,
    val productName: String,
    val cardUid: String,
    val paymentMethod: String,
    val price: Double,
    val timestamp: Long
)