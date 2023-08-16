package com.example.home.presentation.viewmodel


import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.usecase.GetUserUseCase
import com.example.home.presentation.state.HomeUiState
import com.example.home.presentation.state.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    /*savedStateHandle: SavedStateHandle*/
) : ViewModel() {

    private val _state: MutableStateFlow<HomeUiState?> = MutableStateFlow(null)
    val state: StateFlow<HomeUiState?> = _state

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            when (val result = getUserUseCase.invoke()) {
                is Resource.Loading -> {
                    HomeUiState.Loading(result.loadingStatus)
                }

                is Resource.Success -> {
                    HomeUiState.Success(result.data)
                }

                is Resource.Failure -> {
                    HomeUiState.Failure(result.message)
                }
            }.also {
                _state.value = it
            }
        }
    }
}