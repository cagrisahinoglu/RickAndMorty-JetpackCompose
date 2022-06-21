package com.cagrisahinoglu.data.model

import com.cagrisahinoglu.domain.model.Info
import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)

fun InfoResponse.toDomain(): Info {
    return Info(
        next = next,
        prev = prev
    )
}

fun Info.toData(): InfoResponse {
    return InfoResponse(
        next = next,
        prev = prev
    )
}