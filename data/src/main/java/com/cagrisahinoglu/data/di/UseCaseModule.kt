package com.cagrisahinoglu.data.di

import com.cagrisahinoglu.domain.repository.CharacterRepository
import com.cagrisahinoglu.domain.usecase.GetCharacterListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetCharacterListUseCase(characterRepository: CharacterRepository): GetCharacterListUseCase =
        GetCharacterListUseCase(characterRepository)
}