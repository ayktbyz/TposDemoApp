package com.aytbyz.tposdemoapp.data.mapper

import com.aytbyz.tposdemoapp.data.local.entity.SaleEntity
import com.aytbyz.tposdemoapp.domain.model.sale.Sale

fun SaleEntity.toDomain(): Sale =
    Sale(id, productId, productName, cardUid, paymentMethod, price, timestamp)

fun Sale.toEntity(): SaleEntity =
    SaleEntity(id, productId, productName, cardUid, paymentMethod, price, timestamp)