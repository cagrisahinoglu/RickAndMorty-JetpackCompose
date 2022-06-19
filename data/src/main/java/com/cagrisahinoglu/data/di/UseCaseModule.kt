package com.cagrisahinoglu.data.di

import com.cagrisahinoglu.domain.repository.CharacterRepository
import com.cagrisahinoglu.domain.usecase.characters.AddCharacterFavoriteUseCase
import com.cagrisahinoglu.domain.usecase.characters.GetCharacterListUseCase
import com.cagrisahinoglu.domain.usecase.characters.RemoveCharacterFavoriteUseCase
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

    @Provides
    @ViewModelScoped
    fun provideAddFavoriteCharacterUseCase(characterRepository: CharacterRepository): AddCharacterFavoriteUseCase =
        AddCharacterFavoriteUseCase(characterRepository = characterRepository)

    @Provides
    @ViewModelScoped
    fun provideRemoveFavoriteCharacterUseCase(characterRepository: CharacterRepository): RemoveCharacterFavoriteUseCase =
        RemoveCharacterFavoriteUseCase(characterRepository = characterRepository)
}