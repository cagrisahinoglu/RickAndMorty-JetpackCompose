package com.cagrisahinoglu.data.remote.api

import com.cagrisahinoglu.data.model.ResultResponse
import com.cagrisahinoglu.data.utils.RemoteConstants
import com.cagrisahinoglu.domain.model.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET(RemoteConstants.CHARACTER_LIST)
    suspend fun getCharacterList(
        @Query(RemoteConstants.CHARACTER_LIST_PAGE) page: Int
    ): ResultResponse

    @GET(RemoteConstants.CHARACTER_LIST)
    suspend fun searchCharacters(
        @Query(RemoteConstants.CHARACTER_LIST_PAGE) page: Int,
        @Query(RemoteConstants.CHARACTER_LIST_NAME_SEARCH) name: String,
    ): ResultResponse

    @GET(RemoteConstants.CHARACTER_LIST + "/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int,
    ): Character?
}