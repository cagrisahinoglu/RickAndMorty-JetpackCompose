package com.cagrisahinoglu.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("result")
    val results: List<Result>
)