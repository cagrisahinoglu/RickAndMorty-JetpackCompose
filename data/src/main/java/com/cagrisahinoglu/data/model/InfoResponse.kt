package com.cagrisahinoglu.data.model

import com.cagrisahinoglu.domain.model.Info
import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("prev")
    val prev: String?
)

fun InfoResponse.toDomain(): Info {
    return Info(
        count = count,
        next = next,
        pages = pages,
        prev = prev
    )
}

fun Info.toData(): InfoResponse {
    return InfoResponse(
        count = count,
        next = next,
        pages = pages,
        prev = prev
    )
}