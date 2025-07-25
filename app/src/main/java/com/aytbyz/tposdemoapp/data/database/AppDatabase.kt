package com.aytbyz.tposdemoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aytbyz.tposdemoapp.data.local.dao.ProductDao
import com.aytbyz.tposdemoapp.data.local.dao.SaleDao
import com.aytbyz.tposdemoapp.data.local.entity.ProductEntity
import com.aytbyz.tposdemoapp.data.local.entity.SaleEntity

@Database(
    entities = [ProductEntity::class, SaleEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun saleDao(): SaleDao
}