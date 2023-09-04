package com.example.cryptocurrencyapp.common

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()

    fun toData(): T? {
        return if (this is Success) {
            data
        } else {
            null
        }
    }
}