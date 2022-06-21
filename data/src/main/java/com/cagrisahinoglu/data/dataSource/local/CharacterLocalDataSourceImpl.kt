package com.cagrisahinoglu.data.dataSource.local

import androidx.paging.*
import com.cagrisahinoglu.data.dataSource.BaseDataSource
import com.cagrisahinoglu.data.local.CharacterRemoteMediator
import com.cagrisahinoglu.data.local.dao.CharacterDao
import com.cagrisahinoglu.data.local.dao.FavoriteCharacterDao
import com.cagrisahinoglu.data.model.entities.toDomain
import com.cagrisahinoglu.data.model.entities.toFavoriteEntity
import com.cagrisahinoglu.data.remote.api.CharacterService
import com.cagrisahinoglu.domain.dataSource.local.CharactersLocalDataSource
import com.cagrisahinoglu.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterLocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val favoriteCharacterDao: FavoriteCharacterDao,
    private val characterService: CharacterService
) : BaseDataSource(), CharactersLocalDataSource {
    override fun getAllCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharacterRemoteMediator(
                characterService = characterService,
                characterDao = characterDao
            )
        ) {
            characterDao.getAllCharacters()
        }.flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getAllFavoriteCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 20)
        ) {
            favoriteCharacterDao.getAllFavoriteCharacters()
        }.flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFavoriteCharacterById(id: Int): List<Character> =
        favoriteCharacterDao.getFavoriteCharacterById(id).map { it.toDomain() }

    override suspend fun insertCharacterFavorite(character: Character) =
        favoriteCharacterDao.insertCharacter(character.toFavoriteEntity())

    override suspend fun removeCharacterFavorite(character: Character) =
        favoriteCharacterDao.deleteCharacter(character.toFavoriteEntity())
}