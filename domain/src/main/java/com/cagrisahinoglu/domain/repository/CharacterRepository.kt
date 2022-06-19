package com.cagrisahinoglu.domain.repository

import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.model.Result

interface CharacterRepository {
    suspend fun getCharacterList(page: Int): Result
    suspend fun insertCharacter(character: Character)
    suspend fun deleteCharacter(character: Character)
    suspend fun checkIsCharacterFavorite(characterId: Int): List<Character>
}