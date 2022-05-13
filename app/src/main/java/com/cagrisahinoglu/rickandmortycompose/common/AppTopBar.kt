package com.cagrisahinoglu.rickandmortycompose.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AppTopBar(
    barBody: @Composable () -> Unit
) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        barBody()
    }
}