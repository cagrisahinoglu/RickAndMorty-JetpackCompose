package com.cagrisahinoglu.domain.usecase.characters

import com.cagrisahinoglu.domain.repository.CharacterRepository

class GetCharacterListUseCase(
    private val repository: CharacterRepository,
) {
    operator fun invoke() = repository.getAllCharacters()
}

