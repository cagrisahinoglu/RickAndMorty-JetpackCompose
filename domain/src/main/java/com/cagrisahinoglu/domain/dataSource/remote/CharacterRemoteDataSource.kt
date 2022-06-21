package com.cagrisahinoglu.domain.dataSource.remote

import androidx.paging.PagingData
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDataSource {
    fun searchCharacters(searchText: String): Flow<PagingData<Character>>
    suspend fun getSingleCharacter(id: Int): Character?
}