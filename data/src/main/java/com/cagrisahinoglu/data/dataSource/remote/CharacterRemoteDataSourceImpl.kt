package com.cagrisahinoglu.data.dataSource.remote

import com.cagrisahinoglu.data.dataSource.BaseDataSource
import com.cagrisahinoglu.data.model.toDomain
import com.cagrisahinoglu.data.remote.api.CharacterService
import com.cagrisahinoglu.domain.dataSource.CharacterRemoteDataSource
import com.cagrisahinoglu.domain.model.Character
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
): BaseDataSource() ,CharacterRemoteDataSource {
    override suspend fun getCharacterList(page: Int): Character = characterService.getCharacterList(page).toDomain()
}