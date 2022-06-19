package com.cagrisahinoglu.rickandmortycompose.util

sealed class ViewState<out T> {
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val e: Exception) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    object NoResult: ViewState<Nothing>()
}

