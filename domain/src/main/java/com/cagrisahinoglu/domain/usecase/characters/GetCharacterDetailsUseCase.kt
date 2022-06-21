package com.cagrisahinoglu.domain.usecase.characters

import com.cagrisahinoglu.domain.repository.CharacterRepository

class GetCharacterDetailsUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(id: Int) = characterRepository.getCharacterById(id)
}
