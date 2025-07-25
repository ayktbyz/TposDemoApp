package com.aytbyz.tposdemoapp.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.*
import com.aytbyz.tposdemoapp.presentation.theme.TPosDemoAppTheme
import com.aytbyz.tposdemoapp.presentation.ui.components.navbar.MainScaffold
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val startupViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TPosDemoAppTheme {
                val navController = rememberNavController()
                MainScaffold(navController)
            }
        }
    }
}