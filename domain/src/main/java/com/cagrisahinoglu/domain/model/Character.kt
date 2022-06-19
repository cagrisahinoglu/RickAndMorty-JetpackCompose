package com.cagrisahinoglu.domain.model


data class Character(
    val created: String = "",
    val episode: List<String> = emptyList(),
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val location: Location? = null,
    val name: String = "",
    val origin: Origin? = null,
    val species: String = "",
    val status: String = "",
    val type: String = "",
    val url: String = "",
    var liveStatus: CharacterLiveStatus = CharacterLiveStatus.UNKNOWN,
    var isFav: Boolean = false
)
enum class CharacterLiveStatus {
    ALIVE,
    DEAD,
    UNKNOWN
}

