package com.cagrisahinoglu.data.model

import com.cagrisahinoglu.data.model.entities.CharacterEntity
import com.cagrisahinoglu.domain.model.Character
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String
)

fun CharacterResponse.toDomain(): Character {
    return Character(
        gender = gender,
        id = id,
        image = image,
        name = name,
        species = species,
        status = status,
        isFav = false
    )
}

fun Character.toData(): CharacterResponse {
    return CharacterResponse(
        gender = gender,
        id = id,
        image = image,
        name = name,
        species = species,
        status = status
    )
}

fun CharacterResponse.toEntity(
    prev: Int? = null,
    next: Int? = null
): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        species = species,
        gender = gender,
        status = status,
        image = image,
        prev = prev,
        next = next
    )
}