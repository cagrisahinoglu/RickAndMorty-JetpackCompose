package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cagrisahinoglu.domain.model.Result

@Composable
fun CharacterListingItem(
    item: Result
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.image)
                .crossfade(true)
                .build(),
            contentDescription = "avatar",
            modifier = Modifier.size(70.dp)
        )
        Spacer(
            modifier = Modifier.width(10.dp)
        )
        Column{
            Text(
                text = item.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.height(5.dp)
            )
            Text(
                text = item.status + " " + item.species,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}