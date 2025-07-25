package com.aytbyz.tposdemoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class SaleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val productName: String,
    val cardUid: String,
    val paymentMethod: String,
    val price: Double,
    val timestamp: Long
)