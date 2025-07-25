package com.aytbyz.tposdemoapp.presentation.ui.components.navbar

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aytbyz.tposdemoapp.R
import com.aytbyz.tposdemoapp.presentation.navigation.NavRoutes
import com.aytbyz.tposdemoapp.presentation.ui.home.MainScreen
import com.aytbyz.tposdemoapp.presentation.ui.nfc_payment.NfcPaymentActivity
import com.aytbyz.tposdemoapp.presentation.ui.product_list.ProductListScreen
import com.aytbyz.tposdemoapp.presentation.ui.qr_payment.QrFlowEntryPoint
import com.aytbyz.tposdemoapp.presentation.ui.sales.SalesListScreen
import com.aytbyz.tposdemoapp.presentation.ui.settings.SettingsScreen

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val context = androidx.compose.ui.platform.LocalContext.current

    NavHost(
        navController = navController, startDestination = NavRoutes.Home.route, modifier = modifier
    ) {
        composable(NavRoutes.Home.route) {
            MainScreen(
                onSelectQr = { navController.navigate(NavRoutes.Qr.route) },
                onSelectNfc = {
                    val intent = Intent(context, NfcPaymentActivity::class.java)
                    context.startActivity(intent)
                })
        }

        composable(NavRoutes.Products.route) {
            ProductListScreen()
        }

        composable(NavRoutes.Sales.route) { SalesListScreen() }

        composable(NavRoutes.Qr.route) {
            val message = stringResource(id = R.string.camera_permission_denied)

            QrFlowEntryPoint(onBack = { navController.popBackStack() }, onShowPermissionDenied = {
                Toast.makeText(
                    context, message, Toast.LENGTH_LONG
                ).show()
            })
        }

        composable(NavRoutes.Settings.route) { SettingsScreen() }
    }
}