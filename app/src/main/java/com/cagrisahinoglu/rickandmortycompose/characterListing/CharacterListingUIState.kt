package com.cagrisahinoglu.rickandmortycompose.characterListing

/**
 * Created by cagrisahinoglu on 3.07.2022.
 */
sealed interface CharacterListingUIState<out T> {
    data class Response<T>(val data: T) : CharacterListingUIState<T>
    object Loading : CharacterListingUIState<Nothing>
}