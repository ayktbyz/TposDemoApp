package com.aytbyz.tposdemoapp.data.mapper

import com.aytbyz.tposdemoapp.data.local.entity.ProductEntity
import com.aytbyz.tposdemoapp.domain.model.product.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl
    )
}