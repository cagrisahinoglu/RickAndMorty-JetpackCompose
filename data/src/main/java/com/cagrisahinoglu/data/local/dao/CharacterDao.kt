package com.cagrisahinoglu.data.local.dao

import androidx.room.*
import com.cagrisahinoglu.data.model.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao: BaseDao<CharacterEntity> {

    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun checkIsCharacterFavorite(characterId: Int): List<CharacterEntity>

    @Query("SELECT * FROM characters")
    fun getAllFavoriteCharacters(): List<CharacterEntity>
}