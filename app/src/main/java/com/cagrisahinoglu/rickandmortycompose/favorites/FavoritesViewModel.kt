package com.cagrisahinoglu.rickandmortycompose.favorites

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.GetAllFavoriteCharactersUseCase
import com.cagrisahinoglu.domain.usecase.characters.RemoveCharacterFavoriteUseCase
import com.cagrisahinoglu.domain.util.DataState
import com.cagrisahinoglu.rickandmortycompose.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoriteCharactersUseCase: GetAllFavoriteCharactersUseCase,
    private val removeCharacterFavoriteUseCase: RemoveCharacterFavoriteUseCase
) : ViewModel() {

    private val _viewState: MutableState<ViewState<List<Character>>> =
        mutableStateOf(ViewState.Loading)
    val viewState: State<ViewState<List<Character>>>
        get() = _viewState

    fun getFavoriteCharacterList() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            getAllFavoriteCharactersUseCase()
                .collect { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            if(dataState.data.isEmpty()) {
                                _viewState.value = ViewState.NoResult
                            } else {
                                _viewState.value = ViewState.Success(
                                    data = dataState.data
                                )
                            }
                        }
                        is DataState.Error -> {
                            _viewState.value = ViewState.Error(
                                e = dataState.exception
                            )
                        }
                        is DataState.Loading -> {
                            _viewState.value = ViewState.Loading
                        }
                    }
                }
        }
    }

    fun removeFavoriteCharacter(character: Character) {
        viewModelScope.launch {
            removeCharacterFavoriteUseCase(character = character)
            getFavoriteCharacterList()
        }
    }
}