package com.cagrisahinoglu.domain.repository

import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.model.Result
import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacterList(page: Int): Result
    suspend fun insertCharacter(character: Character)
    suspend fun deleteCharacter(character: Character)
    suspend fun checkIsCharacterFavorite(characterId: Int): List<Character>
    fun getAllCharacters(): Flow<PagingData<Character>>
}