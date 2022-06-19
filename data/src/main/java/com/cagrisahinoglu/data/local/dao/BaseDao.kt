package com.cagrisahinoglu.data.local.dao

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(entity: T)

    @Update
    fun updateCharacter(entity: T)

    @Delete
    fun deleteCharacter(entity: T)
}