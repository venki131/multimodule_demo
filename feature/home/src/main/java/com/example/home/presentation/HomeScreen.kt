package com.example.home.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.home.domain.entity.User
import com.example.home.presentation.state.HomeUiEvent

import com.example.ui.ErrorSnackBar
import com.example.ui.Loading

@Composable
fun HomeScreen(
    state: HomeUiEvent,
    modifier: Modifier = Modifier
) {
    when (state) {
        is HomeUiEvent.Loading -> Loading(modifier)
        is HomeUiEvent.Success -> UserCard(users = state.data)
        is HomeUiEvent.Failure -> ErrorSnackBar(modifier, message = "Dummy Error Message"/*state.message*/)
    }
}

@Composable
fun UserCard(users: List<User>? = emptyList()) {
    LazyColumn {
        items(users as List<User>) { user ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = ShapeDefaults.Small
            ) {
                Text(
                    text = user.name,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}