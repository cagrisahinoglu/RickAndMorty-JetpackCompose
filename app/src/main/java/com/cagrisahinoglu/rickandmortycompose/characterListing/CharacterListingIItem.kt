package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cagrisahinoglu.domain.model.Result

@Composable
fun CharacterListingItem(
    item: Result
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
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