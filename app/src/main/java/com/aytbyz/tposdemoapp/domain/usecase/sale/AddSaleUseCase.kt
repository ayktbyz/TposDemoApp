package com.aytbyz.tposdemoapp.domain.usecase.sale

import com.aytbyz.tposdemoapp.domain.model.sale.Sale
import com.aytbyz.tposdemoapp.domain.repository.SaleRepository

class AddSaleUseCase(private val repo: SaleRepository) {
    suspend operator fun invoke(sale: Sale) = repo.addSale(sale)
}