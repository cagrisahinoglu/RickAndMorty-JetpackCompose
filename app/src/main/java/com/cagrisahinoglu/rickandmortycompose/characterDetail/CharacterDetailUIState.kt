package com.cagrisahinoglu.rickandmortycompose.characterDetail

/**
 * Created by cagrisahinoglu on 3.07.2022.
 */
sealed interface CharacterDetailUIState<out T> {
    data class Success<T>(val data: T) : CharacterDetailUIState<T>
    data class Error(val e: Exception? = Exception()) : CharacterDetailUIState<Nothing>
    object Loading : CharacterDetailUIState<Nothing>
    object NoResult : CharacterDetailUIState<Nothing>
}