package com.aytbyz.tposdemoapp.presentation.ui.components.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aytbyz.tposdemoapp.presentation.navigation.NavRoutes
import com.aytbyz.tposdemoapp.presentation.ui.components.navbar.model.BottomNavItem
import com.aytbyz.tposdemoapp.R

@Composable
fun MainScaffold(navController: NavHostController) {
    val bottomItems = listOf(NavRoutes.Home, NavRoutes.Products, NavRoutes.Sales, NavRoutes.Settings)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in bottomItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                val navItems = listOf(
                    BottomNavItem(NavRoutes.Home.route, Icons.Default.Home, stringResource(R.string.home_title)),
                    BottomNavItem(NavRoutes.Products.route, Icons.Default.Menu,  stringResource(R.string.products_title)),
                    BottomNavItem(NavRoutes.Sales.route, Icons.Default.List,  stringResource(R.string.sales_title)),
                    BottomNavItem(NavRoutes.Settings.route, Icons.Default.Settings,  stringResource(R.string.settings_title))
                )
                BaseCustomNavBottomBar(navController, navItems)
            }
        }
    ) { innerPadding ->
        MainNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}