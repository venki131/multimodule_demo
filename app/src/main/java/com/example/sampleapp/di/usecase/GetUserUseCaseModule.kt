package com.example.sampleapp.di.usecase


import com.example.home.domain.repository.UserRepository
import com.example.home.domain.usecase.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetUserUseCaseModule {
    @Provides
    @Singleton
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }
}
