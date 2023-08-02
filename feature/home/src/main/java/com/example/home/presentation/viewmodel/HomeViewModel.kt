package com.example.home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.entity.User
import com.example.home.domain.usecase.GetUserUseCase
import kotlinx.coroutines.launch

class HomeViewModel /*@Inject*/ constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> get() = _users

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = getUserUseCase()
                _users.value = users
            } catch (e: Exception) {

            }
        }
    }
}