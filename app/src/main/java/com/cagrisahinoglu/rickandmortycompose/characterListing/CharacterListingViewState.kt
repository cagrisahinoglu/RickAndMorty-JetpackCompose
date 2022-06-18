package com.cagrisahinoglu.rickandmortycompose.characterListing

sealed class CharacterListingViewState<out T> {
    data class Success<T>(val data: T) : CharacterListingViewState<T>()
    data class Error(val e: Exception) : CharacterListingViewState<Nothing>()
    object Loading : CharacterListingViewState<Nothing>()
}

