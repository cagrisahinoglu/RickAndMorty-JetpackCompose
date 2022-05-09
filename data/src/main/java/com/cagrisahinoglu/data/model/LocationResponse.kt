package com.cagrisahinoglu.data.model

import com.cagrisahinoglu.domain.model.Location
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun LocationResponse.toDomain(): Location {
    return Location(
        name = name,
        url = url
    )
}

fun Location.toData(): LocationResponse {
    return LocationResponse(
        name = name,
        url = url
    )
}