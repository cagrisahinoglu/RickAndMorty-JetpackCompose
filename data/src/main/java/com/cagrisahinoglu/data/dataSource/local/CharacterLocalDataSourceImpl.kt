package com.cagrisahinoglu.data.dataSource.local

import com.cagrisahinoglu.data.dataSource.BaseDataSource
import com.cagrisahinoglu.data.local.dao.CharacterDao
import com.cagrisahinoglu.data.model.entities.toDomain
import com.cagrisahinoglu.data.model.entities.toEntity
import com.cagrisahinoglu.domain.dataSource.local.CharactersLocalDataSource
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterLocalDataSourceImpl @Inject constructor(
    private val dao: CharacterDao
) : BaseDataSource(), CharactersLocalDataSource {
    override suspend fun insertCharacter(character: Character) =
        dao.insertCharacter(character.toEntity())

    override suspend fun deleteCharacter(character: Character) =
        dao.deleteCharacter(character.toEntity())

    override suspend fun checkIsCharacterFavorite(characterId: Int): List<Character> =
        dao.checkIsCharacterFavorite(characterId).map {
            it.toDomain()
        }

    override fun getAllFavoriteCharacter(): Flow<DataState<List<Character>>> =
        getResultAsFlow { dao.getAllFavoriteCharacters().map { it.toDomain() }}
}