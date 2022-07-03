package com.cagrisahinoglu.rickandmortycompose.util

sealed interface ViewState<out T> {
    data class Success<T>(val data: T) : ViewState<T>
    data class Error(val e: Exception? = Exception()) : ViewState<Nothing>
    object Loading : ViewState<Nothing>
    object NoResult: ViewState<Nothing>
    object MissingCharacter: ViewState<Nothing>
}

