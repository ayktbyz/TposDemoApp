package com.aytbyz.tposdemoapp

import android.app.Application
import android.util.Log
import com.aytbyz.tposdemoapp.domain.repository.ProductRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class TPosDemoApplication : Application() {

    @Inject
    lateinit var productRepository: ProductRepository

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("App", "Initializing dummy products")
            productRepository.initializeProductsIfNeeded()
        }
    }
}