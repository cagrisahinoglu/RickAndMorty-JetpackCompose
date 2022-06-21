package com.cagrisahinoglu.data.di

import com.cagrisahinoglu.domain.repository.CharacterRepository
import com.cagrisahinoglu.domain.usecase.characters.*
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
    fun provideUpdateFavoriteStatusUseCase(characterRepository: CharacterRepository): UpdateFavoriteStatusUseCase =
        UpdateFavoriteStatusUseCase(characterRepository = characterRepository)

    @Provides
    @ViewModelScoped
    fun provideRemoveCharacterFavoriteUseCase(characterRepository: CharacterRepository): RemoveCharacterFavoriteUseCase =
        RemoveCharacterFavoriteUseCase(characterRepository = characterRepository)

    @Provides
    @ViewModelScoped
    fun provideGetAllFavoriteCharactersUseCase(characterRepository: CharacterRepository): GetAllFavoriteCharactersUseCase =
        GetAllFavoriteCharactersUseCase(characterRepository = characterRepository)

    @Provides
    @ViewModelScoped
    fun provideGetCharacterDetailUseCase(characterRepository: CharacterRepository): GetCharacterDetailsUseCase =
        GetCharacterDetailsUseCase(characterRepository = characterRepository)

    @Provides
    @ViewModelScoped
    fun provideSearchCharacterUseCase(characterRepository: CharacterRepository): SearchCharacterUseCase =
        SearchCharacterUseCase(characterRepository = characterRepository)

    @Provides
    @ViewModelScoped
    fun provideGetSingleCharacterUseCase(characterRepository: CharacterRepository): GetSingleCharacterUseCase =
        GetSingleCharacterUseCase(characterRepository = characterRepository)
}