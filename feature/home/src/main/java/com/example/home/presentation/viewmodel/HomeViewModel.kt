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

    private val _state: MutableStateFlow<HomeUiEvent?> = MutableStateFlow(null)
    val state: StateFlow<HomeUiEvent?> = _state

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
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
                _state.value = it
            }
        }
    }
}