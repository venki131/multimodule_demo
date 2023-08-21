package com.example.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.data.User
import com.example.home.presentation.state.HomeUiEvent
import com.example.ui.ErrorSnackBar
import com.example.ui.Loading

@Composable
fun HomeScreen(
    onUserClick: (User) -> Unit,
    state: HomeUiEvent,
    modifier: Modifier = Modifier
) {
    when (state) {
        is HomeUiEvent.Loading -> Loading(modifier)
        is HomeUiEvent.Success -> UserCard(users = state.data, onUserClick)
        is HomeUiEvent.Failure -> ErrorSnackBar(
            modifier,
            message = state.message
        )
    }
}

@Composable
fun UserCard(
    users: List<User>? = emptyList(),
    onUserCardClick: (User) -> Unit
) {
    LazyColumn {
        items(users as List<User>) { user ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        onUserCardClick(user)
                    },
                shape = ShapeDefaults.Small
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = user.name,
                        modifier = Modifier.padding(16.dp),
                    )
                    val favoriteState = remember { mutableStateOf(user.isFavourite) }
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourite",
                        tint = if (favoriteState.value) Color.Red else Color.Gray,
                        modifier = Modifier
                            .clickable {
                                favoriteState.value = !favoriteState.value
                                user.isFavourite = favoriteState.value
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}