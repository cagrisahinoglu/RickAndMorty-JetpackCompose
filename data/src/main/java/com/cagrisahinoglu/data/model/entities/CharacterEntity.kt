package com.cagrisahinoglu.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cagrisahinoglu.data.utils.LocalConstants
import com.cagrisahinoglu.domain.model.Character

@Entity(tableName = LocalConstants.CHARACTER_TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
)

fun CharacterEntity.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        species = species,
        gender = gender,
        status = status,
    )
}

fun Character.toEntity(): CharacterEntity =
    CharacterEntity(
        id = id,
        name = name,
        species = species,
        gender = gender,
        status = status,
    )