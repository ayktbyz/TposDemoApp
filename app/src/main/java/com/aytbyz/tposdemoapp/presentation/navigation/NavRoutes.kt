package com.aytbyz.tposdemoapp.presentation.navigation

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object Products : NavRoutes("products")
    data object Sales : NavRoutes("sales")

    data object Qr : NavRoutes("qr")
    data object Nfc : NavRoutes("nfc")
}