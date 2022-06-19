package com.cagrisahinoglu.domain.dataSource.remote

import com.cagrisahinoglu.domain.model.Result

interface CharacterRemoteDataSource {
    suspend fun getCharacterList(page: Int): Result
}