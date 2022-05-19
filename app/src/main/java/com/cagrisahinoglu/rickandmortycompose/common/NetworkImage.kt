package com.cagrisahinoglu.rickandmortycompose.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cagrisahinoglu.rickandmortycompose.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    crossfade: Boolean = true
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    Box {

        if(isLoading) {
            CircularProgressIndicator()
        }
        AsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(crossfade)
                .error(R.drawable.placeholder)
                .build(),
            contentDescription = "networkImage",
            onSuccess = {
                isLoading = false
            },
            onError = {
                isLoading = false
            }
        )
    }
}