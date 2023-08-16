package com.example.sampleapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.home.presentation.viewmodel.HomeViewModel
import com.example.navigation.BottomNavigationBar
import com.example.navigation.BottomNavItem
import com.example.navigation.addMainGraph
import com.example.sampleapp.ui.theme.SampleAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    appTheme: @Composable() ((Modifier) -> Unit?)?,
    homeViewModel: HomeViewModel? = null
) {
    SampleAppTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController, appTheme = appTheme)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                addMainGraph(navController, homeViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppNavigationPreview() {
    val navController = rememberNavController()
    AppNavigation(navController = navController, null)
}
