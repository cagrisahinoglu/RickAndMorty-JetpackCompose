package com.cagrisahinoglu.domain.repository

import com.cagrisahinoglu.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacterList(page: Int): Character
}