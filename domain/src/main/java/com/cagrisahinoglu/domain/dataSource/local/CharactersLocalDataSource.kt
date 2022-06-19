package com.cagrisahinoglu.domain.dataSource.local

import com.cagrisahinoglu.domain.model.Character

interface CharactersLocalDataSource {
    suspend fun insertCharacter(character: Character)
    suspend fun deleteCharacter(character: Character)
    suspend fun checkIsCharacterFavorite(characterId: Int): List<Character>
}