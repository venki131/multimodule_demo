package com.example.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.home.data.repository.UserRepositoryImpl
import com.example.home.domain.entity.User
import com.example.home.domain.usecase.GetUserUseCase
import com.example.home.presentation.fragments.HomeScreen
import com.example.home.presentation.viewmodel.HomeViewModel
import com.example.network.ApiClient
import com.example.network.ApiService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(viewModel: HomeViewModel) {
    val users: State<List<User>> = viewModel.users

    HomeScreen(users)
    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        // Add the rest of your home screen content here
    }*/
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    HomeScreenContent(HomeViewModel(GetUserUseCase(UserRepositoryImpl(apiClient = ApiClient))))
}
