package com.cagrisahinoglu.rickandmortycompose.characterListing

import com.cagrisahinoglu.domain.model.Character

data class CharacterListingViewState(
    var characters: Character? = null,
    var isLoading: Boolean = false,
    var errorMessage: String = ""
)
