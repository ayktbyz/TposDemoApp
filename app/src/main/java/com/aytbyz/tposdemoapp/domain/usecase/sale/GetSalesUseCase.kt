package com.aytbyz.tposdemoapp.domain.usecase.sale

import com.aytbyz.tposdemoapp.domain.model.sale.Sale
import com.aytbyz.tposdemoapp.domain.repository.SaleRepository

class GetSalesUseCase(private val repo: SaleRepository) {
    suspend operator fun invoke(): List<Sale> = repo.getSales()
}