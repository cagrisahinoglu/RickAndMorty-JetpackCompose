package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.GetCharacterListUseCase
import com.cagrisahinoglu.domain.usecase.characters.UpdateFavoriteStatusUseCase
import com.cagrisahinoglu.rickandmortycompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListingViewModel
@Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) : BaseViewModel<CharacterListingUIState<Flow<PagingData<Character>>>>() {

    init {
        getCharacters()
    }

    private fun getCharacters() {
        setState(CharacterListingUIState.Loading)
        viewModelScope.launch() {
            delay(500)
            val response = getCharacterListUseCase()
            setState(
                CharacterListingUIState.Response(
                    data = response
                )
            )
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

    override fun setDefaultUIState(): CharacterListingUIState<Flow<PagingData<Character>>> =
        CharacterListingUIState.Loading

}