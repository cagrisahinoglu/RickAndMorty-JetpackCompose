package com.cagrisahinoglu.rickandmortycompose.search

/**
 * Created by cagrisahinoglu on 3.07.2022.
 */
sealed interface SearchPageUIState<out T> {
    data class Success<T>(val data: T) : SearchPageUIState<T>
    data class Error(val e: Exception? = Exception()) : SearchPageUIState<Nothing>
    object Loading : SearchPageUIState<Nothing>
    object MissingCharacter : SearchPageUIState<Nothing>
}