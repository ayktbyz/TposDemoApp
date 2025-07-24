package com.aytbyz.tposdemoapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.aytbyz.tposdemoapp.presentation.navigation.NavRoutes
import com.aytbyz.tposdemoapp.presentation.theme.ui.TposDemoAppTheme
import com.aytbyz.tposdemoapp.presentation.ui.main.MainScreen
import com.aytbyz.tposdemoapp.presentation.ui.sales.SalesListScreen
import com.aytbyz.tposdemoapp.domain.model.Sale
import com.aytbyz.tposdemoapp.presentation.ui.nfc_payment.NfcPaymentScreen
import com.aytbyz.tposdemoapp.presentation.ui.product_list.ProductListScreen
import com.aytbyz.tposdemoapp.presentation.ui.qr_payment.QrPaymentScreen
import com.aytbyz.tposdemoapp.presentation.ui.settings.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TposDemoAppTheme {
                val navController = rememberNavController()
                MainScaffold(navController)
            }
        }
    }
}

@Composable
fun MainScaffold(navController: NavHostController) {
    val bottomItems =
        listOf(NavRoutes.Home, NavRoutes.Products, NavRoutes.Sales, NavRoutes.Settings)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in bottomItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomItems.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                when (screen) {
                                    NavRoutes.Home -> Icon(
                                        Icons.Default.Home,
                                        contentDescription = null
                                    )

                                    NavRoutes.Products -> Icon(
                                        Icons.Default.ShoppingCart,
                                        contentDescription = null
                                    )

                                    NavRoutes.Sales -> Icon(
                                        Icons.Default.List,
                                        contentDescription = null
                                    )
                                    NavRoutes.Settings -> Icon(
                                        Icons.Default.Settings,
                                        contentDescription = null
                                    )

                                    NavRoutes.Nfc -> TODO()
                                    NavRoutes.Qr -> TODO()
                                }
                            },
                            label = { Text(stringResource(id = screen.titleRes)) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.Home.route) {
                MainScreen()
            }

            composable(NavRoutes.Products.route) {
                ProductListScreen(
                    onSelectQr = { navController.navigate(NavRoutes.Qr.route) },
                    onSelectNfc = { navController.navigate(NavRoutes.Nfc.route) },
                    onSelectLoyalty = { navController.navigate(NavRoutes.Nfc.route) }
                )
            }

            composable(NavRoutes.Sales.route) {
                val dummySales = listOf<Sale>()
                SalesListScreen(sales = dummySales, onDelete = {})
            }

            composable(NavRoutes.Qr.route) {
                QrPaymentScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable(NavRoutes.Nfc.route) {
                NfcPaymentScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable(NavRoutes.Settings.route) {
                SettingsScreen()
            }
        }
    }
}