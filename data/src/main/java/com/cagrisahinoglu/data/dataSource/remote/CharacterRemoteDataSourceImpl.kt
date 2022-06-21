package com.cagrisahinoglu.data.dataSource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cagrisahinoglu.data.dataSource.BaseDataSource
import com.cagrisahinoglu.data.model.toDomain
import com.cagrisahinoglu.data.pagination.SearchCharacterPagingSource
import com.cagrisahinoglu.data.remote.api.CharacterService
import com.cagrisahinoglu.domain.dataSource.remote.CharacterRemoteDataSource
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.model.Result
import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : BaseDataSource(), CharacterRemoteDataSource {
    override fun searchCharacters(searchText: String): Flow<PagingData<Character>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        SearchCharacterPagingSource(
            characterService = characterService,
            searchText = searchText
        )
    }.flow.flowOn(Dispatchers.IO)

    override suspend fun getSingleCharacter(id: Int): Character? =
        characterService.getSingleCharacter(id)
}