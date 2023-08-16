package com.example.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ErrorSnackBar(
    modifier: Modifier = Modifier,
    message: String? = ""
) {
    var snackBarVisible by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        Column {
            Button(
                onClick = { snackBarVisible = true },
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = message ?: "Unknown Error")
            }
        }
    }
}

@Preview
@Composable
fun ErrorSnackBarPreview() {
    ErrorSnackBar(
        modifier = Modifier.padding(16.dp),
        message = "Simple snackbar"
    )
}