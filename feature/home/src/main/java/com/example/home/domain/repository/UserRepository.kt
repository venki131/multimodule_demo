package com.example.home.domain.repository

import com.example.data.Resource
import com.example.data.User

interface UserRepository {
    suspend fun getUsers() : Resource<List<User>>
}