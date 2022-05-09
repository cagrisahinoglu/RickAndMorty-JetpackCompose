package com.cagrisahinoglu.data.remote.api

import com.cagrisahinoglu.data.model.CharacterResponse
import com.cagrisahinoglu.data.utils.RemoteConstants
import retrofit2.http.GET

interface CharacterService {
    @GET(RemoteConstants.CHARACTER_LIST)
    suspend fun getCharacterList(): CharacterResponse

}