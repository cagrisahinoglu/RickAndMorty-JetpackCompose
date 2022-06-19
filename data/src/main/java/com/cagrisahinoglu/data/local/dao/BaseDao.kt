package com.cagrisahinoglu.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BaseDao<T> {
    @Insert
    fun insertCharacter(entity: T)

    @Update
    fun updateCharacter(entity: T)

    @Delete
    fun deleteCharacter(entity: T)
}