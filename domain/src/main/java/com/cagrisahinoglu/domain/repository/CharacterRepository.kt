package com.cagrisahinoglu.domain.repository

import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.model.Result
import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<PagingData<Character>>
    fun getAllFavoriteCharacters(): Flow<PagingData<Character>>
    fun getCharacterById(characterId: Int): Flow<DataState<Character?>>
    suspend fun insertCharacterFavorite(character: Character)
    suspend fun removeCharacterFavorite(character: Character)
}