package com.cagrisahinoglu.data.repository

import com.cagrisahinoglu.domain.dataSource.local.CharactersLocalDataSource
import com.cagrisahinoglu.domain.dataSource.remote.CharacterRemoteDataSource
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.model.Result
import com.cagrisahinoglu.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource
) : CharacterRepository {
    override suspend fun getCharacterList(
        page: Int
    ): Result = characterRemoteDataSource.getCharacterList(
        page = page
    )

    override suspend fun insertCharacter(character: Character) =
        charactersLocalDataSource.insertCharacter(character)

    override suspend fun deleteCharacter(character: Character) =
        charactersLocalDataSource.deleteCharacter(character)

    override suspend fun checkIsCharacterFavorite(characterId: Int): List<Character> =
        charactersLocalDataSource.checkIsCharacterFavorite(characterId)
}