package com.cagrisahinoglu.domain.usecase.characters

import com.cagrisahinoglu.domain.repository.CharacterRepository

class GetAllFavoriteCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    //operator fun invoke() = characterRepository.getAllFavoriteCharacters()
}