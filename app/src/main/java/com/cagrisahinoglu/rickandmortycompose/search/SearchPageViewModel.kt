package com.cagrisahinoglu.rickandmortycompose.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.SearchCharacterUseCase
import com.cagrisahinoglu.domain.usecase.characters.UpdateFavoriteStatusUseCase
import com.cagrisahinoglu.rickandmortycompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(
    private val searchCharacterUseCase: SearchCharacterUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) : BaseViewModel<SearchPageUIState<Flow<PagingData<Character>>>>() {

    var searchText by mutableStateOf("")

    fun searchCharacter() {
        setState(SearchPageUIState.Loading)
        if (searchText.length > 1) {
            viewModelScope.launch {
                delay(500)
                val response = searchCharacterUseCase(searchText)
                setState(
                    SearchPageUIState.Success(
                        data = response
                    )
                )
            }
        } else {
            setState(SearchPageUIState.MissingCharacter)
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

    override fun setDefaultUIState(): SearchPageUIState<Flow<PagingData<Character>>> =
        SearchPageUIState.MissingCharacter
}