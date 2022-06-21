package com.cagrisahinoglu.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cagrisahinoglu.data.utils.LocalConstants
import com.cagrisahinoglu.domain.model.Character

@Entity(tableName = LocalConstants.FAVORITE_CHARACTER_TABLE_NAME)
data class FavoriteCharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
    val image: String,
    val prev: Int? = null,
    val next: Int? = null
)

fun FavoriteCharacterEntity.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        species = species,
        gender = gender,
        status = status,
        image = image
    )
}

fun Character.toFavoriteEntity(): FavoriteCharacterEntity {
    return FavoriteCharacterEntity(
        id = id,
        name = name,
        species = species,
        gender = gender,
        status = status,
        image = image
    )
}