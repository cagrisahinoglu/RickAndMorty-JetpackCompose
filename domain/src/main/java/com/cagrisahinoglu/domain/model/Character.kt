package com.cagrisahinoglu.domain.model


data class Character(
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val species: String = "",
    val status: String = "",
    var isFav: Boolean = false
)

