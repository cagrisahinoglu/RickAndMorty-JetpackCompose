package com.cagrisahinoglu.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.cagrisahinoglu.domain.dataSource.local.CharactersLocalDataSource
import com.cagrisahinoglu.domain.dataSource.remote.CharacterRemoteDataSource
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.model.Result
import com.cagrisahinoglu.domain.repository.CharacterRepository
import com.cagrisahinoglu.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val charactersLocalDataSource: CharactersLocalDataSource
) : CharacterRepository {
    override fun getAllCharacters(): Flow<PagingData<Character>> =
        charactersLocalDataSource.getAllCharacters().map {
            it.map { character ->
                val favoriteCharacter =
                    charactersLocalDataSource.getFavoriteCharacterById(character.id)
                val isFav = favoriteCharacter.isNotEmpty()
                character.isFav = isFav
                character
            }
        }

    override fun getAllFavoriteCharacters(): Flow<PagingData<Character>> =
        charactersLocalDataSource.getAllFavoriteCharacters()

    override fun getCharacterById(characterId: Int): Flow<DataState<Character?>> =
        charactersLocalDataSource.getCharacterById(characterId)

    override suspend fun insertCharacterFavorite(character: Character) =
        charactersLocalDataSource.insertCharacterFavorite(character)

    override suspend fun removeCharacterFavorite(character: Character) =
        charactersLocalDataSource.removeCharacterFavorite(character)
}