package com.aytbyz.tposdemoapp.data.repository

import com.aytbyz.tposdemoapp.data.local.dao.ProductDao
import com.aytbyz.tposdemoapp.data.mapper.toDomain
import com.aytbyz.tposdemoapp.data.mapper.toEntity
import com.aytbyz.tposdemoapp.domain.model.product.Product
import com.aytbyz.tposdemoapp.domain.model.product.ProductUIDummy
import com.aytbyz.tposdemoapp.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val dao: ProductDao
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return dao.getAllProducts().map { it.toDomain() }
    }

    override suspend fun saveProducts(products: List<Product>) {
        val entities = products.map { it.toEntity() }
        dao.insertAll(entities)
    }

    override suspend fun clearProducts() {
        dao.clearAll()
    }

    override suspend fun initializeProductsIfNeeded() {
        val count = dao.getProductCount()
        if (count == 0) {
            val dummyProducts = ProductUIDummy.getDummyProducts().map { it.toEntity() }
            dao.insertAll(dummyProducts)
        }
    }

    override suspend fun getRandomProduct(): Product {
        return dao.getRandomProduct().toDomain()
    }
}