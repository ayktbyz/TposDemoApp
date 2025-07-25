package com.aytbyz.tposdemoapp.data.repository

import com.aytbyz.tposdemoapp.data.local.dao.SaleDao
import com.aytbyz.tposdemoapp.data.mapper.toDomain
import com.aytbyz.tposdemoapp.data.mapper.toEntity
import com.aytbyz.tposdemoapp.domain.model.sale.Sale
import com.aytbyz.tposdemoapp.domain.repository.SaleRepository

class SaleRepositoryImpl(
    private val dao: SaleDao
) : SaleRepository {
    override suspend fun getSales(): List<Sale> = dao.getAllSales().map { it.toDomain() }
    override suspend fun addSale(sale: Sale) = dao.insertSale(sale.toEntity())
    override suspend fun deleteSale(id: Int) = dao.deleteSaleById(id)
}