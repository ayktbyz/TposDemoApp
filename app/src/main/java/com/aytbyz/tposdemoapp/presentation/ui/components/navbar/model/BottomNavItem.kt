package com.aytbyz.tposdemoapp.presentation.ui.components.navbar.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)