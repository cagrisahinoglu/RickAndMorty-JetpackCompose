package com.cagrisahinoglu.domain.dataSource.local

import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {
    fun getAllCharacters(): Flow<PagingData<Character>>
    fun getAllFavoriteCharacters(): Flow<PagingData<Character>>
    suspend fun getFavoriteCharacterById(id: Int): List<Character>
    suspend fun insertCharacterFavorite(character: Character)
    suspend fun removeCharacterFavorite(character: Character)
}