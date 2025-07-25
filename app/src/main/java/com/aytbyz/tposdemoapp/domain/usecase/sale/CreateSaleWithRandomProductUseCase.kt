package com.aytbyz.tposdemoapp.domain.usecase.sale

import com.aytbyz.tposdemoapp.domain.model.enum.PaymentType
import com.aytbyz.tposdemoapp.domain.model.sale.Sale
import com.aytbyz.tposdemoapp.domain.repository.ProductRepository
import com.aytbyz.tposdemoapp.domain.repository.SaleRepository

class CreateSaleWithRandomProductUseCase(
    private val productRepository: ProductRepository,
    private val saleRepository: SaleRepository
) {
    suspend operator fun invoke(cardUid: String): Sale {
        val randomProduct = productRepository.getRandomProduct()

        val sale = Sale(
            productId = randomProduct.id.toInt(),
            productName = randomProduct.name,
            cardUid = cardUid,
            paymentMethod = PaymentType.NFC.value,
            price = randomProduct.price,
            timestamp = System.currentTimeMillis()
        )

        saleRepository.addSale(sale)
        return sale
    }
}