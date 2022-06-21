package com.cagrisahinoglu.data.di

import android.content.Context
import androidx.room.Room
import com.cagrisahinoglu.data.local.dao.CharacterDao
import com.cagrisahinoglu.data.local.dao.FavoriteCharacterDao
import com.cagrisahinoglu.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app-database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideCharactersDao(db: AppDatabase): CharacterDao = db.charactersDao()

    @Provides
    @Singleton
    fun provideFavoriteCharactersDao(db: AppDatabase): FavoriteCharacterDao = db.favoriteCharactersDao()
}