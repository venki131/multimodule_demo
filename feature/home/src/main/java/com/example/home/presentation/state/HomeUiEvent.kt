package com.example.home.presentation.state

import com.example.home.domain.entity.User

sealed interface HomeUiEvent {
    data class Loading(val loadingStatus: Boolean) : HomeUiEvent
    data class Success(
        val data: List<User>? = emptyList()
    ) : HomeUiEvent

    data class Failure(val message: String?) : HomeUiEvent
}

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val loadingStatus: Boolean = false
) {
    class Loading<T>(loadingStatus: Boolean, data: T? = null) :
        Resource<T>(data, loadingStatus = loadingStatus)

    class Success<T>(data: T?) : Resource<T>(data)
    class Failure<T>(message: String, data: T? = null) : Resource<T>(data, message = message)
}