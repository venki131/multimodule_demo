package com.example.home.data.repository

import com.example.home.data.mapper.UserMapper
import com.example.home.domain.entity.User
import com.example.home.domain.repository.UserRepository
import com.example.home.presentation.state.Resource
import com.example.network.ApiService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(): Resource<List<User>> {
        val networksUsers = apiService.getUsers().map { UserMapper.mapToDomain(it) }
        return try {
            Resource.Loading(
                loadingStatus = true,
                data = null
            )
            Resource.Success(networksUsers)
        } catch (e: Exception) {
            Resource.Failure(
                message = "Failure",
                data = null
            )
        }
    }
}