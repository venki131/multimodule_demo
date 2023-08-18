package com.example.home

import com.example.home.data.mapper.UserMapper
import com.example.home.data.repository.UserRepositoryImpl
import com.example.home.domain.entity.User
import com.example.home.domain.repository.UserRepository
import com.example.home.presentation.state.Resource
import com.example.network.ApiService
import io.mockk.every
import io.mockk.mockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import user_info.NetworkUser

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImplTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockApiService: ApiService

    private lateinit var repository: UserRepository
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = UserRepositoryImpl(mockApiService, dispatcher = Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `getUsers Success`() = testScope.runBlockingTest {
        //Given
        val mockNetworkUsers = listOf(NetworkUser(1,"James", "james@yopmail.com"))
        val mockDomainUser = User(1,"James","james@yopmail.com")

        `when`(mockApiService.getUsers()).thenReturn(mockNetworkUsers)

        mockkObject(UserMapper) //use mockk to mock singleton class
        every { UserMapper.mapToDomain(mockNetworkUsers[0]) } returns mockDomainUser


        //When
        val result = repository.getUsers()

        //Then
        val expectedSuccess = Resource.Success(
            data = listOf(mockDomainUser)
        )
        assertTrue(result is Resource.Success && result.data == expectedSuccess.data)
    }

    @Test
    fun `getUsers returns emptyList`() = testScope.runBlockingTest {
        //Given
        `when`(mockApiService.getUsers()).thenReturn(emptyList())

        //When
        val result = repository.getUsers()

        //Then
        assertTrue(result is Resource.Success)
        assertNotNull((result as Resource.Success).data)
    }

    @Test
    fun `getUsers failure`() = testScope.runBlockingTest {
        // Given
        val errorMessage = "Sample error message"
        // Mock the API service response
        `when`(mockApiService.getUsers()).thenThrow(RuntimeException(errorMessage))

        // When
        val result = repository.getUsers()

        // Then
        assertTrue(result is Resource.Failure)
        assertEquals(errorMessage, (result as Resource.Failure).message)
    }
}