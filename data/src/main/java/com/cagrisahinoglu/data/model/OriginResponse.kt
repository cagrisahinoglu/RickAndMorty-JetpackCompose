package com.cagrisahinoglu.data.model

import com.cagrisahinoglu.domain.model.Origin
import com.google.gson.annotations.SerializedName

data class OriginResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun OriginResponse.toDomain(): Origin {
    return Origin(
        name = name,
        url = url
    )
}

fun Origin.toData(): OriginResponse {
    return OriginResponse(
        name = name,
        url = url
    )
}