package com.cagrisahinoglu.rickandmortycompose.favorites

/**
 * Created by cagrisahinoglu on 3.07.2022.
 */
sealed interface FavoritesUIState<out T> {
    data class Response<T>(val data: T) : FavoritesUIState<T>
    object Loading : FavoritesUIState<Nothing>
}