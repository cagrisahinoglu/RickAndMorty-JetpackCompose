package com.cagrisahinoglu.domain.dataSource.local

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {
    suspend fun insertCharacter(character: Character)
    suspend fun deleteCharacter(character: Character)
    suspend fun checkIsCharacterFavorite(characterId: Int): List<Character>
    fun getAllCharacters(): Flow<PagingData<Character>>
}