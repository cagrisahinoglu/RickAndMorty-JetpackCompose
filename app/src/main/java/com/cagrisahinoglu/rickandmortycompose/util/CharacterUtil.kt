package com.cagrisahinoglu.rickandmortycompose.util

import androidx.compose.ui.graphics.Color

fun getColorForLiveStatus(status: String): Color {
    return when (status) {
        "Alive" -> Color.Cyan
        "Dead" -> Color.Red
        else -> Color.Gray
    }
}