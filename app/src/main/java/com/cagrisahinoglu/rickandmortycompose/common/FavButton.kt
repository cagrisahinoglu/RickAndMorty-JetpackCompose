package com.cagrisahinoglu.rickandmortycompose.common

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavButton(
    isFav: Boolean,
    modifier: Modifier = Modifier,
    onFavButtonClick: () -> Unit,
) {
    Icon(
        Icons.Default.Favorite,
        tint = if(isFav) Color.Red else Color.Gray,
        contentDescription = "",
        modifier = modifier.clickable {
            onFavButtonClick()
        }
    )
}
