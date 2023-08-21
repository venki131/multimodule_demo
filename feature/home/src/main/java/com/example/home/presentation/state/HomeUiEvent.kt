package com.example.home.presentation.state

sealed interface HomeUiEvent {
    data class Loading(val loadingStatus: Boolean) : HomeUiEvent
    data class Success(
        val data: List<com.example.data.User>? = emptyList()
    ) : HomeUiEvent

    data class Failure(val message: String?) : HomeUiEvent
}