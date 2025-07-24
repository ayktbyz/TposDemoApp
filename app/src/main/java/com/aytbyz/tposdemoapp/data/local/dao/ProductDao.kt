package com.aytbyz.tposdemoapp.data.local.dao

import androidx.room.*
import com.aytbyz.tposdemoapp.data.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductCount(): Int
}