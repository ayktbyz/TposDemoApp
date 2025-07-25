package com.aytbyz.tposdemoapp.di

import android.app.Application
import androidx.room.Room
import com.aytbyz.tposdemoapp.common.Constants
import com.aytbyz.tposdemoapp.data.database.AppDatabase
import com.aytbyz.tposdemoapp.data.local.dao.ProductDao
import com.aytbyz.tposdemoapp.data.local.dao.SaleDao
import com.aytbyz.tposdemoapp.data.repository.ProductRepositoryImpl
import com.aytbyz.tposdemoapp.data.repository.SaleRepositoryImpl
import com.aytbyz.tposdemoapp.domain.repository.ProductRepository
import com.aytbyz.tposdemoapp.domain.repository.SaleRepository
import com.aytbyz.tposdemoapp.domain.usecase.product.GetProductsUseCase
import com.aytbyz.tposdemoapp.domain.usecase.sale.AddSaleUseCase
import com.aytbyz.tposdemoapp.domain.usecase.sale.CreateSaleWithRandomProductUseCase
import com.aytbyz.tposdemoapp.domain.usecase.sale.DeleteSaleUseCase
import com.aytbyz.tposdemoapp.domain.usecase.sale.GetSalesUseCase
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
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    fun provideSaleDao(db: AppDatabase): SaleDao = db.saleDao()

    @Provides
    @Singleton
    fun provideProductRepository(dao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideSaleRepository(dao: SaleDao): SaleRepository {
        return SaleRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        repository: ProductRepository
    ): GetProductsUseCase {
        return GetProductsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSalesUseCase(
        repository: SaleRepository
    ): GetSalesUseCase {
        return GetSalesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteSaleUseCase(
        repository: SaleRepository
    ): DeleteSaleUseCase {
        return DeleteSaleUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddSaleUseCase(
        repository: SaleRepository
    ): AddSaleUseCase {
        return AddSaleUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCreateSaleWithRandomProductUseCase(
        productRepository: ProductRepository,
        saleRepository: SaleRepository
    ): CreateSaleWithRandomProductUseCase {
        return CreateSaleWithRandomProductUseCase(productRepository, saleRepository)
    }
}