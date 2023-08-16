package com.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.favourites.FavouriteScreenContent
import com.example.home.HomeRoute
import com.example.home.presentation.viewmodel.HomeViewModel
import com.example.settings.SettingsScreenContent

fun NavGraphBuilder.addMainGraph(navController: NavHostController, homeViewModel: HomeViewModel? = null) {
    composable(BottomNavItem.Home.route) {
        homeViewModel?.let {
            HomeRoute(homeViewModel)
        }
    }
    composable(BottomNavItem.Favourites.route) {
        FavouriteScreenContent()
    }
    composable(BottomNavItem.Settings.route) {
        SettingsScreenContent()
    }
}

