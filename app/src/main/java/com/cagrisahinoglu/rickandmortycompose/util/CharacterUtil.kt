package com.cagrisahinoglu.rickandmortycompose.util

import androidx.compose.ui.graphics.Color
import com.cagrisahinoglu.domain.model.CharacterLiveStatus

fun getColorForLiveStatus(liveStatus: CharacterLiveStatus): Color {
    return when(liveStatus) {
        CharacterLiveStatus.ALIVE -> Color.Cyan
        CharacterLiveStatus.DEAD -> Color.Red
        else -> Color.Gray
    }
}