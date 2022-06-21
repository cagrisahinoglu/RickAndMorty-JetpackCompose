package com.cagrisahinoglu.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cagrisahinoglu.data.model.entities.CharacterEntity

@Dao
interface CharacterDao : BaseDao<CharacterEntity> {
    @Query("SELECT * FROM characters order by id ASC")
    fun getAllCharacters(): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM characters where id = :id")
    fun getCharacterById(id: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacterList(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    fun deleteAllCharacters()
}