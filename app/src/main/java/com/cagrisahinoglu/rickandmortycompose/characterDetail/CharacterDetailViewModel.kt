package com.cagrisahinoglu.rickandmortycompose.characterDetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.usecase.characters.GetCharacterDetailsUseCase
import com.cagrisahinoglu.domain.usecase.characters.GetSingleCharacterUseCase
import com.cagrisahinoglu.domain.util.DataState
import com.cagrisahinoglu.rickandmortycompose.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase
) : ViewModel() {

    private val _viewState: MutableState<ViewState<Character>> = mutableStateOf(ViewState.Loading)
    val viewState: State<ViewState<Character>>
        get() = _viewState

    fun getCharacterDetail(id: Int) {
        viewModelScope.launch {
            getCharacterDetailsUseCase(id).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if(dataState.data == null) {
                            val response = getSingleCharacterUseCase(id)
                            if(response == null) {
                                _viewState.value = ViewState.NoResult
                            } else {
                                _viewState.value = ViewState.Success(response)
                            }
                        } else {
                            _viewState.value = ViewState.Success(dataState.data!!)
                        }
                    }
                    is DataState.Error -> {
                        _viewState.value = ViewState.Error(dataState.exception)
                    }
                    DataState.Loading -> {
                        _viewState.value = ViewState.Loading
                    }
                }
            }
        }
    }
}