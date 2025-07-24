package com.aytbyz.tposdemoapp.presentation.navigation

import androidx.annotation.StringRes
import com.aytbyz.tposdemoapp.R

sealed class NavRoutes(val route: String, @StringRes val titleRes: Int) {
    data object Home : NavRoutes("home", R.string.home_title)
    data object Products : NavRoutes("products", R.string.products_title)
    data object Sales : NavRoutes("sales", R.string.sales_title)
    data object Settings : NavRoutes("settings", R.string.settings_title)

    data object Qr : NavRoutes("qr", R.string.qr_payment_title)
    data object Nfc : NavRoutes("nfc", R.string.nfc_payment_title)
}