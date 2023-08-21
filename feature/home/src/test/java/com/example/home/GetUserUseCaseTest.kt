package com.example.home

import com.example.data.User
import com.example.home.domain.repository.UserRepository
import com.example.home.domain.usecase.GetUserUseCase
import com.example.home.presentation.state.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetUserUseCaseTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: UserRepository
    private lateinit var userUseCase: GetUserUseCase

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userUseCase = GetUserUseCase(repository)
    }

    @Test
    fun `invoke return Loading`() = runTest {
        //Given
        `when`(repository.getUsers()).thenReturn(Resource.Loading(true))

        //When
        val result = userUseCase.invoke()

        //Then
        assertTrue(result is Resource.Loading)
    }

    @Test
    fun `invoke return Success`() = runTest {
        //Given
        val mockUsers = listOf(com.example.data.User(1, "James", "jamesbon007@yopmail.com"))
        `when`(repository.getUsers()).thenReturn(Resource.Success(mockUsers))

        //When
        val result = userUseCase.invoke()

        //Then
        assertTrue(result is Resource.Success)
        assertEquals(mockUsers, (result as Resource.Success).data)
    }

    @Test
    fun `invoke return Failure`() = runTest {
        //Given
        val errorMessage = "Sample error message"
        `when`(repository.getUsers()).thenReturn(Resource.Failure(errorMessage))

        //When
        val result = userUseCase.invoke()

        //Then
        assertTrue(result is Resource.Failure)
        assertEquals(errorMessage, (result as Resource.Failure).message)
    }
}