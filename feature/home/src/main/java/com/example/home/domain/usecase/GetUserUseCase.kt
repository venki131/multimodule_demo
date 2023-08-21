package com.example.home.domain.usecase

import com.example.data.User
import com.example.home.domain.repository.UserRepository
import com.example.home.presentation.state.Resource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Resource<List<com.example.data.User>> {
        return userRepository.getUsers()
    }
}