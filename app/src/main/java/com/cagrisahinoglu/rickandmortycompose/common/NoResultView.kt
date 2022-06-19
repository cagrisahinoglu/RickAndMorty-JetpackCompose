package com.cagrisahinoglu.rickandmortycompose.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.compose.*

@Composable
fun NoResultView(
    modifier: Modifier = Modifier,
    animSize: Dp,
    animFileRes: Int,
    text: String
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animFileRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            modifier = modifier.size(animSize),
            progress = {
                progress
            }
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}