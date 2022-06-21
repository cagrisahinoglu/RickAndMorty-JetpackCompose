package com.cagrisahinoglu.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cagrisahinoglu.data.local.dao.CharacterDao
import com.cagrisahinoglu.data.model.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
}