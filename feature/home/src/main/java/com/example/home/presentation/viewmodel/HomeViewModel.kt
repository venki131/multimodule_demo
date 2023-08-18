package com.example.home.presentation.viewmodel


import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.usecase.GetUserUseCase
import com.example.home.presentation.state.HomeUiEvent
import com.example.home.presentation.state.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiEvent?> = MutableStateFlow(null)
    val uiState: StateFlow<HomeUiEvent?> = _uiState

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            when (val result = getUserUseCase.invoke()) {
                is Resource.Loading -> {
                    HomeUiEvent.Loading(result.loadingStatus)
                }

                is Resource.Success -> {
                    HomeUiEvent.Success(result.data)
                }

                is Resource.Failure -> {
                    HomeUiEvent.Failure(result.message)
                }
            }.also {
                _uiState.value = it
            }
        }
    }
}