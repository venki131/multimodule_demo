package com.example.home

import com.example.home.domain.entity.User
import com.example.home.domain.usecase.GetUserUseCase
import com.example.home.presentation.state.HomeUiEvent
import com.example.home.presentation.state.Resource
import com.example.home.presentation.viewmodel.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Rule
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
class HomeViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var getUserUseCase: GetUserUseCase

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        // Create the ViewModel with the mocked dependencies
        homeViewModel = HomeViewModel(getUserUseCase)

    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `fetchUsers success`() = testDispatcher.runBlockingTest {
        // Given
        val mockUserList =
            listOf(User(id = 1, name = "James Bond", email = "Jamesbond007@yopmail.com"))
        val successResource = Resource.Success(mockUserList)
        `when`(getUserUseCase.invoke()).thenReturn(successResource)

        val uiStateObserver: MutableList<HomeUiEvent?> = mutableListOf()

        val job = launch {
            homeViewModel.uiState.collect { uiStateObserver.add(it) }
        }

        // When
        homeViewModel.fetchUsers()

        // Wait for the job to complete
        job.cancelAndJoin()

        // Then
        assertEquals(2, uiStateObserver.size)

        val successEvent = uiStateObserver[1] as HomeUiEvent
        assertEquals(HomeUiEvent.Success(mockUserList), successEvent)
    }

    @Test
    fun `fetchUsers Loading`() = testDispatcher.runBlockingTest {
        // Given
        val mockUserList =
            listOf(User(id = 1, name = "James Bond", email = "Jamesbond007@yopmail.com"))
        val loadingResource = Resource.Loading(true, mockUserList)
        `when`(getUserUseCase.invoke()).thenReturn(loadingResource)

        val uiStateObserver: MutableList<HomeUiEvent?> = mutableListOf()

        val job = launch {
            homeViewModel.uiState.collect {
                uiStateObserver.add(it)
            }
        }

        //when
        homeViewModel.fetchUsers()

        job.cancelAndJoin()

        // Then
        assertEquals(2, uiStateObserver.size)

        val loadingEvent = uiStateObserver[1] as HomeUiEvent
        assertEquals(HomeUiEvent.Loading(true), loadingEvent)
    }

    @Test
    fun `fetchUsers throws Error`() = testDispatcher.runBlockingTest {
        // Given
        val errorMessage = "Sample error message"
        val failureResource = Resource.Failure<List<User>>(errorMessage)
        `when`(getUserUseCase.invoke()).thenReturn(failureResource)

        val uiStateObserver: MutableList<HomeUiEvent?> = mutableListOf()

        val job = launch {
            homeViewModel.uiState.collect {
                uiStateObserver.add(it)
            }
        }

        //When
        homeViewModel.fetchUsers()

        job.cancelAndJoin()

        //Then
        assertEquals(2, uiStateObserver.size)

        val errorEvent = uiStateObserver[1] as HomeUiEvent
        assertEquals(HomeUiEvent.Failure(errorMessage), errorEvent)
    }
}





