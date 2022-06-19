package com.cagrisahinoglu.data.di

import com.cagrisahinoglu.data.dataSource.remote.CharacterRemoteDataSourceImpl
import com.cagrisahinoglu.data.repository.CharacterRepositoryImpl
import com.cagrisahinoglu.domain.dataSource.CharacterRemoteDataSource
import com.cagrisahinoglu.domain.repository.CharacterRepository
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
}