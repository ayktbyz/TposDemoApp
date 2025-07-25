package com.aytbyz.tposdemoapp.domain.repository

import com.aytbyz.tposdemoapp.domain.model.sale.Sale

interface SaleRepository {
    suspend fun getSales(): List<Sale>
    suspend fun addSale(sale: Sale)
    suspend fun deleteSale(id: Int)
}