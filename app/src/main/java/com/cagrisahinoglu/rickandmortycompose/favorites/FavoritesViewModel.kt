package com.cagrisahinoglu.rickandmortycompose.favorites

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.GetAllFavoriteCharactersUseCase
import com.cagrisahinoglu.domain.usecase.characters.RemoveCharacterFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoriteCharactersUseCase: GetAllFavoriteCharactersUseCase,
    private val removeCharacterFavoriteUseCase: RemoveCharacterFavoriteUseCase
) : ViewModel() {

    private var _characters: MutableState<Flow<PagingData<Character>>>? = mutableStateOf(emptyFlow())
    val characters: State<Flow<PagingData<Character>>>?
        get() = _characters

    fun getFavoriteCharacterList() {
        viewModelScope.launch {
            delay(500)
            val response = getAllFavoriteCharactersUseCase()
            _characters?.value = response
        }
    }

    fun removeFavoriteCharacter(character: Character) {
        viewModelScope.launch {
            removeCharacterFavoriteUseCase(character)
        }
    }
}