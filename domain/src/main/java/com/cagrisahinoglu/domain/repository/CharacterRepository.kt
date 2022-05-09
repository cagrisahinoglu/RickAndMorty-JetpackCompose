package com.cagrisahinoglu.domain.repository

import com.cagrisahinoglu.domain.model.ApiResponse
import com.cagrisahinoglu.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacterList(): Flow<ApiResponse<List<Character>>>

}