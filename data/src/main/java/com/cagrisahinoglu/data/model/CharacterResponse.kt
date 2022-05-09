package com.cagrisahinoglu.data.model

import com.cagrisahinoglu.domain.model.Character
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info: InfoResponse,
    @SerializedName("result")
    val result: List<ResultResponse>
)

fun CharacterResponse.toDomain(): Character {
    return Character(
        info = info.toDomain(),
        results = result.map {
            it.toDomain()
        }
    )
}

fun Character.toData(): CharacterResponse {
    return CharacterResponse(
        info = info.toData(),
        result = results.map {
            it.toData()
        }
    )
}