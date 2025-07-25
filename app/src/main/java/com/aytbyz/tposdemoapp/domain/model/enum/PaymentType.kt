package com.aytbyz.tposdemoapp.domain.model.enum

enum class PaymentType(val value: String) {
    QR("QR"),
    NFC("NFC");

    override fun toString(): String = value
}