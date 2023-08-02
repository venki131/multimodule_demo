package com.example.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home: BottomNavItem("home", Icons.Default.Home, "Home")
    object Favourites: BottomNavItem("favourites", Icons.Default.Favorite, "Favourites")
    object Settings: BottomNavItem("settings", Icons.Default.Settings, "Settings")
}
