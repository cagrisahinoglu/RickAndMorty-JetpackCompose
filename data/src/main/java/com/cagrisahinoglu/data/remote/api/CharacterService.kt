package com.cagrisahinoglu.data.remote.api

import com.cagrisahinoglu.data.model.ResultResponse
import com.cagrisahinoglu.data.utils.RemoteConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET(RemoteConstants.CHARACTER_LIST)
    suspend fun getCharacterList(
        @Query(RemoteConstants.CHARACTER_LIST_PAGE) page: Int
    ): ResultResponse

}