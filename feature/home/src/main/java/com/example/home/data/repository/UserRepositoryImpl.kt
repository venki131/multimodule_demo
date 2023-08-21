package com.example.home.data.repository

import com.example.data.Resource
import com.example.data.User
import com.example.di.IoDispatcher
import com.example.home.data.mapper.UserMapper
import com.example.home.domain.repository.UserRepository
import com.example.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun getUsers(): Resource<List<User>> {
        return try {
            val networksUsers =
                withContext(dispatcher) {
                    apiService.getUsers().map { UserMapper.mapToDomain(it) }
                }
            Resource.Success(networksUsers)
        } catch (e: Exception) {
            Resource.Failure(
                message = e.localizedMessage ?: "unknown error",
                data = null
            )
        }
    }
}