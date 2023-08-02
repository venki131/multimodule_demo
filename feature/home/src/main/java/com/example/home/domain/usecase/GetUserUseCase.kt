package com.example.home.domain.usecase

import com.example.home.domain.entity.User
import com.example.home.domain.repository.UserRepository

class GetUserUseCase (private val userRepository: UserRepository) {
    suspend operator fun invoke() : List<User> {
        return userRepository.getUsers()
    }
}