package com.cagrisahinoglu.rickandmortycompose.characterDetail

import androidx.lifecycle.viewModelScope
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.GetCharacterDetailsUseCase
import com.cagrisahinoglu.domain.usecase.characters.GetSingleCharacterUseCase
import com.cagrisahinoglu.domain.util.DataState
import com.cagrisahinoglu.rickandmortycompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase
) : BaseViewModel<CharacterDetailUIState<Character>>() {

    fun getCharacterDetail(id: Int) {
        viewModelScope.launch {
            getCharacterDetailsUseCase(id).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if (dataState.data == null) {
                            val response = getSingleCharacterUseCase(id)
                            if (response == null) {
                                setState(CharacterDetailUIState.NoResult)
                            } else {
                                setState(CharacterDetailUIState.Success(response))
                            }
                        } else {
                            setState(CharacterDetailUIState.Success(dataState.data!!))
                        }
                    }
                    is DataState.Error -> {
                        setState(CharacterDetailUIState.Error(dataState.exception))
                    }
                    DataState.Loading -> {
                        setState(CharacterDetailUIState.Loading)
                    }
                }
            }
        }
    }

    override fun setDefaultUIState(): CharacterDetailUIState<Character> =
        CharacterDetailUIState.Loading
}