package com.cagrisahinoglu.data.model

import com.google.gson.annotations.SerializedName
import com.cagrisahinoglu.domain.model.Result

data class ResultResponse(
    @SerializedName("info")
    val info: InfoResponse,
    @SerializedName("results")
    val results: List<CharacterResponse>
)

fun ResultResponse.toDomain(): Result {
    return Result(
        info = info.toDomain(),
        characters = results.map {
            it.toDomain()
        }
    )
}

fun Result.toData(): ResultResponse {
    return ResultResponse(
        info = info.toData(),
        results = characters.map {
            it.toData()
        }
    )
}