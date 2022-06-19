package com.cagrisahinoglu.data.model

import com.cagrisahinoglu.domain.model.CharacterLiveStatus
import com.cagrisahinoglu.domain.model.Character
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
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
    val location: LocationResponse?,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: OriginResponse?,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

fun CharacterResponse.toDomain(): Character {
    return Character(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location?.toDomain(),
        name = name,
        origin = origin?.toDomain(),
        species = species,
        status = status,
        type = type,
        url = url,
        isFav = false,
        liveStatus = when (status) {
            "Alive" -> {
                CharacterLiveStatus.ALIVE
            }
            "Dead" -> {
                CharacterLiveStatus.DEAD
            }
            else -> {
                CharacterLiveStatus.UNKNOWN
            }
        }
    )
}

fun Character.toData(): CharacterResponse {
    return CharacterResponse(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location?.toData(),
        name = name,
        origin = origin?.toData(),
        species = species,
        status = status,
        type = type,
        url = url
    )
}