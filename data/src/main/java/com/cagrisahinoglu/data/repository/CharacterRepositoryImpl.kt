package com.cagrisahinoglu.data.repository

import com.cagrisahinoglu.domain.dataSource.CharacterRemoteDataSource
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
) : CharacterRepository {
    override suspend fun getCharacterList(
        page: Int
    ): Character {
        return characterRemoteDataSource.getCharacterList(
            page = page
        )
    }
}