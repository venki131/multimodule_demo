package com.example.home.presentation.fragments

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import com.example.home.domain.entity.User

@Composable
fun HomeScreen(users: State<List<User>>) {
    LaunchedEffect(Unit) {
        // Fetch users here or trigger the fetch from a ViewModel
    }

    LazyColumn {
        items(users.value) {user ->
            Text(text = user.name)
        }
    }
}