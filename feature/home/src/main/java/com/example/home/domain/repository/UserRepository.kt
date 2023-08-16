package com.example.home.domain.repository

import com.example.home.domain.entity.User
import com.example.home.presentation.state.Resource

interface UserRepository {
    suspend fun getUsers() : Resource<List<User>>
}