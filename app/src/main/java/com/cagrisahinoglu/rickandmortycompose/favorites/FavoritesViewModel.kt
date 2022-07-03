package com.cagrisahinoglu.rickandmortycompose.favorites

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.GetAllFavoriteCharactersUseCase
import com.cagrisahinoglu.domain.usecase.characters.RemoveCharacterFavoriteUseCase
import com.cagrisahinoglu.rickandmortycompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoriteCharactersUseCase: GetAllFavoriteCharactersUseCase,
    private val removeCharacterFavoriteUseCase: RemoveCharacterFavoriteUseCase
) : BaseViewModel<FavoritesUIState<Flow<PagingData<Character>>>>() {

    fun getFavoriteCharacterList() {
        viewModelScope.launch {
            setState(FavoritesUIState.Loading)
            delay(500)
            val response = getAllFavoriteCharactersUseCase()
            setState(
                FavoritesUIState.Response(
                    data = response
                )
            )
        }
    }

    fun removeFavoriteCharacter(character: Character) {
        viewModelScope.launch {
            removeCharacterFavoriteUseCase(character)
        }
    }

    override fun setDefaultUIState(): FavoritesUIState<Flow<PagingData<Character>>> =
        FavoritesUIState.Loading
}