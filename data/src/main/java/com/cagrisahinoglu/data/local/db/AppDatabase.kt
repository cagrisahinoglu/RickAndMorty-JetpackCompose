package com.cagrisahinoglu.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cagrisahinoglu.data.local.dao.CharacterDao
import com.cagrisahinoglu.data.local.dao.FavoriteCharacterDao
import com.cagrisahinoglu.data.model.entities.CharacterEntity
import com.cagrisahinoglu.data.model.entities.FavoriteCharacterEntity

@Database(entities = [CharacterEntity::class, FavoriteCharacterEntity::class], version = 6)
abstract class AppDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
    abstract fun favoriteCharactersDao(): FavoriteCharacterDao
}