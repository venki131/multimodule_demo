package com.example.home.domain.usecase

import com.example.home.domain.entity.User
import com.example.home.domain.repository.UserRepository
import com.example.home.presentation.state.Resource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Resource<List<User>> {
        return userRepository.getUsers()
    }
}