package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cagrisahinoglu.domain.model.Result
import com.cagrisahinoglu.domain.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListingViewModel
@Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {

    private val _viewState: MutableState<CharacterListingViewState<Flow<PagingData<Result>>>> =
        mutableStateOf(CharacterListingViewState.Loading)
    val viewState: State<CharacterListingViewState<Flow<PagingData<Result>>>>
        get() = _viewState

    var selectedCharacter by mutableStateOf<Result?>(null)
        private set

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            val response = Pager(
                PagingConfig(pageSize = 20)
            ) {
                getCharacterListUseCase
            }.flow.cachedIn(viewModelScope)
            _viewState.value = CharacterListingViewState.Success(
                data = response
            )
        }
    }

    fun setCharacter(character: Result?) {
        selectedCharacter = character
    }
}