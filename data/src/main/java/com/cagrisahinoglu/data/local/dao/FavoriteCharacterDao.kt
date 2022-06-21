package com.cagrisahinoglu.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.cagrisahinoglu.data.model.entities.FavoriteCharacterEntity

@Dao
interface FavoriteCharacterDao: BaseDao<FavoriteCharacterEntity> {
    @Query("SELECT * FROM favorite_characters ORDER BY id ASC")
    fun getAllFavoriteCharacters(): PagingSource<Int, FavoriteCharacterEntity>

    @Query("SELECT * FROM favorite_characters where id = :characterId")
    suspend fun getFavoriteCharacterById(characterId: Int): List<FavoriteCharacterEntity>
}