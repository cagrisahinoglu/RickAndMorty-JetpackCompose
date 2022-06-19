package com.cagrisahinoglu.domain.dataSource

import com.cagrisahinoglu.domain.model.Character

interface CharacterRemoteDataSource {
    suspend fun getCharacterList(page: Int): Character
}