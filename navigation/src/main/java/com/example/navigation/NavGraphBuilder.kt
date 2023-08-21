package com.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.data.User
import com.example.favourites.FavouriteRoute
import com.example.home.HomeRoute
import com.example.settings.SettingsScreenContent
import com.squareup.moshi.Moshi

fun NavGraphBuilder.addMainGraph(navController: NavHostController) {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(User::class.java).lenient()
    composable(BottomNavItem.Home.route) {
        HomeRoute(onGoToItem = { user ->
            val userJson = jsonAdapter.toJson(user)
            navController.navigate(
                BottomNavItem.Favourites.route + "/user = $userJson",
            )
        })
    }
    composable(BottomNavItem.Favourites.route) {
        FavouriteRoute()
    }
    composable(
        BottomNavItem.Favourites.route + "/user = {user}",
        deepLinks = listOf(navDeepLink { uriPattern = "android-app://androidx.navigation/favourites/{userJson}" })
    ) { backStackEntry ->
        val user = backStackEntry.arguments?.getString("user") ?: ""
        val userObject = jsonAdapter.fromJson(user)
        FavouriteRoute(userObject)
    }
    composable(BottomNavItem.Settings.route) {
        SettingsScreenContent()
    }
}

