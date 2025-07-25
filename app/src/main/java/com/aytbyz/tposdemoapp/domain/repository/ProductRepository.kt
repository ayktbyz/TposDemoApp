package com.aytbyz.tposdemoapp.domain.repository

import com.aytbyz.tposdemoapp.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun saveProducts(products: List<Product>)
    suspend fun clearProducts()
    suspend fun initializeProductsIfNeeded()
    suspend fun getRandomProduct(): Product
}