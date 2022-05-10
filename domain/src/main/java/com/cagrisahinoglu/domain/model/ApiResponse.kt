package com.cagrisahinoglu.domain.model

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Error(val message: String): ApiResponse<Nothing>()
    object Loading: ApiResponse<Nothing>()
}