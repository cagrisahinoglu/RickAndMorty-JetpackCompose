package com.cagrisahinoglu.data.repository

import com.cagrisahinoglu.data.model.toDomain
import com.cagrisahinoglu.data.remote.api.CharacterService
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService,
) : CharacterRepository {
    override suspend fun getCharacterList(
        page: Int
    ): Character {
        return characterService.getCharacterList(
            page = page
        ).toDomain()
    }
}