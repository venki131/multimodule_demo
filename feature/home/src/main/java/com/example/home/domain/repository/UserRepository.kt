package com.example.home.domain.repository

import com.example.home.domain.entity.User

interface UserRepository {
    suspend fun getUsers() : List<User>
}