package com.aytbyz.tposdemoapp.domain.usecase.product

import com.aytbyz.tposdemoapp.domain.model.product.Product
import com.aytbyz.tposdemoapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> = repository.getProducts()
}