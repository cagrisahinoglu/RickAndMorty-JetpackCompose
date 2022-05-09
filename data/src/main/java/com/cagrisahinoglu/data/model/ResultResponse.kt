package com.cagrisahinoglu.data.model

import com.cagrisahinoglu.domain.model.Result
import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: LocationResponse,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: OriginResponse,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

fun ResultResponse.toDomain(): Result {
    return Result(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location.toDomain(),
        name = name,
        origin = origin.toDomain(),
        species = species,
        status = status,
        type = type,
        url = url
    )
}

fun Result.toData(): ResultResponse {
    return ResultResponse(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location.toData(),
        name = name,
        origin = origin.toData(),
        species = species,
        status = status,
        type = type,
        url = url
    )
}