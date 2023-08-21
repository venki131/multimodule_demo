package com.example.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.home.presentation.HomeScreen
import com.example.home.presentation.viewmodel.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    onGoToItem: (User) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    state?.let {
        HomeScreen(onUserClick = onGoToItem, state = it, modifier = Modifier.padding(16.dp))
    }
}
