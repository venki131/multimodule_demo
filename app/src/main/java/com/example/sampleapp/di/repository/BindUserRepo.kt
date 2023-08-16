package com.example.sampleapp.di.repository

import com.example.home.data.repository.UserRepositoryImpl
import com.example.home.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class BindUserRepo {
    @Binds
    @Singleton
    abstract fun bindRepository(
        userRepository: UserRepositoryImpl
    ): UserRepository
}