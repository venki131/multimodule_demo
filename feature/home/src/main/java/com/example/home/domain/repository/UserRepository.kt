package com.example.home.domain.repository

import com.example.data.User
import com.example.home.presentation.state.Resource

interface UserRepository {
    suspend fun getUsers() : Resource<List<com.example.data.User>>
}