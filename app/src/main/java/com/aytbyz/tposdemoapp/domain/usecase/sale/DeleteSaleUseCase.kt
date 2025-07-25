package com.aytbyz.tposdemoapp.domain.usecase.sale

import com.aytbyz.tposdemoapp.domain.repository.SaleRepository

class DeleteSaleUseCase(private val repo: SaleRepository) {
    suspend operator fun invoke(id: Int) = repo.deleteSale(id)
}