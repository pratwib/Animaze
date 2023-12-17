package com.pratwib.animaze.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val name: String,
    val icon: ImageVector,
    val screen: Screen
)