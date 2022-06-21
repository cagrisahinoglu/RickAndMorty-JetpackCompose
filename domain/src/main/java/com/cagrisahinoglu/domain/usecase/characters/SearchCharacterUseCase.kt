package com.cagrisahinoglu.domain.usecase.characters

import com.cagrisahinoglu.domain.repository.CharacterRepository

class SearchCharacterUseCase(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(searchText: String) = characterRepository.searchCharacter(searchText)
}