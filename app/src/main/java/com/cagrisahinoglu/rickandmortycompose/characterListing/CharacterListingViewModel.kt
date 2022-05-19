package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagrisahinoglu.domain.model.ApiResponse
import com.cagrisahinoglu.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListingViewModel
@Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    var state by mutableStateOf(CharacterListingViewState())

    init {
        getCharacters()
    }

    private fun getCharacters() {
        state = state.copy(isLoading = true)

        viewModelScope.launch {
            characterRepository
                .getCharacterList()
                .collect { response ->
                    when (response) {
                        is ApiResponse.Success -> {
                            state = state.copy(
                                characters = response.data,
                                isLoading = false
                            )
                        }
                        is ApiResponse.Error -> {
                            state = state.copy(
                                errorMessage = response.message,
                                isLoading = false
                            )
                        }
                        is ApiResponse.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                    }
                }
        }
    }
}