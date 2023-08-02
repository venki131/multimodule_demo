package com.example.navigation

import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.favourites.FavouriteScreenContent
import com.example.home.HomeScreenContent
import com.example.settings.SettingsScreenContent

fun NavGraphBuilder.addMainGraph(navController: NavHostController) {
    composable(BottomNavItem.Home.route) {
        HomeScreenContent()
    }
    composable(BottomNavItem.Favourites.route) {
        FavouriteScreenContent()
    }
    composable(BottomNavItem.Settings.route) {
        SettingsScreenContent()
    }
}

