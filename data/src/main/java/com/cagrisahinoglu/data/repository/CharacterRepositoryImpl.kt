package com.cagrisahinoglu.data.repository

import com.cagrisahinoglu.data.model.toDomain
import com.cagrisahinoglu.data.remote.api.CharacterService
import com.cagrisahinoglu.domain.model.ApiResponse
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRepository {
    override fun getCharacterList(): Flow<ApiResponse<Character>> =
        flow {
            emit(ApiResponse.Loading)
            val response = characterService.getCharacterList()
            if (response.isSuccessful) {
                emit(
                    ApiResponse.Success(
                        data = response.body()!!.toDomain()
                    )
                )
            } else {
                emit(
                    ApiResponse.Error(
                        message = "There is a problem: " + response.message() + " code: " + response.code()
                    )
                )
            }
        }
}