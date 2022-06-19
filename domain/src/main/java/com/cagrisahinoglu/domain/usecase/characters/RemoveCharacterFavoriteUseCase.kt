package com.cagrisahinoglu.domain.usecase.characters

import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoveCharacterFavoriteUseCase(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val characterRepository: CharacterRepository
) {

    suspend operator fun invoke(character: Character) {
        withContext(dispatcher) {
            characterRepository.deleteCharacter(character)
        }
    }
}