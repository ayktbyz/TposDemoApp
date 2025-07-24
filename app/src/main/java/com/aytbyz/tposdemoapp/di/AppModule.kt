package com.aytbyz.tposdemoapp.di

import android.app.Application
import androidx.room.Room
import com.aytbyz.tposdemoapp.common.Constants
import com.aytbyz.tposdemoapp.data.database.AppDatabase
import com.aytbyz.tposdemoapp.data.local.dao.ProductDao
import com.aytbyz.tposdemoapp.data.repository.ProductRepositoryImpl
import com.aytbyz.tposdemoapp.domain.repository.ProductRepository
import com.aytbyz.tposdemoapp.domain.usecase.product.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    @Singleton
    fun provideProductRepository(dao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        repository: ProductRepository
    ): GetProductsUseCase {
        return GetProductsUseCase(repository)
    }
}