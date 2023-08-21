package com.example.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteRoute(user: User? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        user?.let { 
            Text(text = user.name + "\n" + user.email)
        } ?: run { 
            Text(text = "Favourites is Empty")
        }
    }
}

@Preview
@Composable
fun FavouriteScreenContentPreview() {
    val user = User(1,"James Bond", "jamesbond007@yopmail.com")
    FavouriteRoute(user)
}