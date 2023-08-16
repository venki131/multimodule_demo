package com.example.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.home.presentation.HomeScreen
import com.example.home.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()
    state?.let {
        HomeScreen(it, modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    //HomeRoute(HomeViewModel(GetUserUseCase(UserRepositoryImpl(apiClient = ApiClient))))
}
