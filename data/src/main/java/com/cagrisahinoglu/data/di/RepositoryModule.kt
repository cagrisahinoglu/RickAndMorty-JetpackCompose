package com.cagrisahinoglu.data.di

import com.cagrisahinoglu.data.repository.CharacterRepositoryImpl
import com.cagrisahinoglu.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun provideCharacterRepository(characterImpl: CharacterRepositoryImpl): CharacterRepository
}