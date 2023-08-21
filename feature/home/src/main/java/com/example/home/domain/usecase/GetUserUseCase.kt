package com.example.home.domain.usecase

import com.example.data.Resource
import com.example.data.User
import com.example.home.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Resource<List<User>> {
        return userRepository.getUsers()
    }
}