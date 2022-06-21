package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.AddCharacterFavoriteUseCase
import com.cagrisahinoglu.domain.usecase.characters.GetCharacterListUseCase
import com.cagrisahinoglu.domain.usecase.characters.RemoveCharacterFavoriteUseCase
import com.cagrisahinoglu.rickandmortycompose.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListingViewModel
@Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val addCharacterFavoriteUseCase: AddCharacterFavoriteUseCase,
    private val removeCharacterFavoriteUseCase: RemoveCharacterFavoriteUseCase
) : ViewModel() {

    private val _viewState: MutableState<ViewState<Flow<PagingData<Character>>>> =
        mutableStateOf(ViewState.Loading)
    val viewState: State<ViewState<Flow<PagingData<Character>>>>
        get() = _viewState

    var selectedCharacter by mutableStateOf<Character?>(null)
        private set

    fun getCharacters() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch() {
            delay(500)
            val response = getCharacterListUseCase()
            _viewState.value = ViewState.Success(
                data = response
            )
        }
    }

    fun updateFavStatus(character: Character, isFav: Boolean) {
        viewModelScope.launch() {
            if (isFav) {
                removeCharacterFavoriteUseCase(character)
            } else {
                addCharacterFavoriteUseCase(character)
            }
        }
    }

    fun setCharacter(character: Character?) {
        selectedCharacter = character
    }
}