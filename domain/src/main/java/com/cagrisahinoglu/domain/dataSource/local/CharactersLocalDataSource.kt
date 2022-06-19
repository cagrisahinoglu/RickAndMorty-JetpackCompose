package com.cagrisahinoglu.domain.dataSource.local

import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {
    suspend fun insertCharacter(character: Character)
    suspend fun deleteCharacter(character: Character)
    suspend fun checkIsCharacterFavorite(characterId: Int): List<Character>
    fun getAllFavoriteCharacter(): Flow<DataState<List<Character>>>
}