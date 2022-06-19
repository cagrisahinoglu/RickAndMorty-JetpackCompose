package com.cagrisahinoglu.data.di

import com.cagrisahinoglu.data.dataSource.local.CharacterLocalDataSourceImpl
import com.cagrisahinoglu.data.dataSource.remote.CharacterRemoteDataSourceImpl
import com.cagrisahinoglu.domain.dataSource.local.CharactersLocalDataSource
import com.cagrisahinoglu.domain.dataSource.remote.CharacterRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun provideCharacterRemoteDataSource(characterRemoteDataSourceImpl: CharacterRemoteDataSourceImpl)
            : CharacterRemoteDataSource

    @Binds
    @Singleton
    fun provideCharacterLocalDataSource(characterLocalDataSourceImpl: CharacterLocalDataSourceImpl)
            : CharactersLocalDataSource
}