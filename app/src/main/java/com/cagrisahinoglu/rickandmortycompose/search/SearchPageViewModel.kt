package com.cagrisahinoglu.rickandmortycompose.search

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.SearchCharacterUseCase
import com.cagrisahinoglu.domain.usecase.characters.UpdateFavoriteStatusUseCase
import com.cagrisahinoglu.rickandmortycompose.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(
    private val searchCharacterUseCase: SearchCharacterUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) : ViewModel() {

    var searchText by mutableStateOf("")

    private var _characters: MutableState<Flow<PagingData<Character>>>? =
        mutableStateOf(emptyFlow())
    val characters: State<Flow<PagingData<Character>>>?
        get() = _characters

    private val _viewState: MutableState<ViewState<Flow<PagingData<Character>>>> =
        mutableStateOf(ViewState.MissingCharacter)
    val viewState: State<ViewState<Flow<PagingData<Character>>>>
        get() = _viewState

    fun searchCharacter() {
        _viewState.value = ViewState.Loading
        if (searchText.length > 1) {
            viewModelScope.launch {
                delay(500)
                val response = searchCharacterUseCase(searchText)
                _characters?.value = searchCharacterUseCase(searchText)
                _viewState.value = ViewState.Success(response)
            }
        } else {
            _characters = mutableStateOf(emptyFlow())
            _viewState.value = ViewState.MissingCharacter
        }
    }

    fun updateFavStatus(character: Character, isFav: Boolean) {
        viewModelScope.launch() {
            updateFavoriteStatusUseCase(
                isFav = isFav,
                character = character
            )
        }
    }
}