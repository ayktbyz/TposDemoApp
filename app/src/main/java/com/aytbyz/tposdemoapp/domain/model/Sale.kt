package com.aytbyz.tposdemoapp.domain.model

data class Sale(
    val productId: String,
    val price: Double,
    val paymentType: String,
    val timestamp: Long
)