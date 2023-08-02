package com.example.home.data.repository

import com.example.home.data.mapper.UserMapper
import com.example.home.domain.entity.User
import com.example.home.domain.repository.UserRepository
import com.example.network.ApiClient

class UserRepositoryImpl(private val apiClient: ApiClient) : UserRepository {
    override suspend fun getUsers(): List<User> {
        val networksUsers = apiClient.apiService.getUsers()
        return networksUsers.map { UserMapper.mapToDomain(it) }
    }
}