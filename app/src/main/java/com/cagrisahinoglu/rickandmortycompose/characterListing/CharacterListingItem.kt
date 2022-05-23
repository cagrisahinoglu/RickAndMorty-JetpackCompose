package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cagrisahinoglu.domain.model.Result
import com.cagrisahinoglu.rickandmortycompose.common.FavButton
import com.cagrisahinoglu.rickandmortycompose.common.NetworkImage
import com.cagrisahinoglu.rickandmortycompose.util.getColorForLiveStatus

@Composable
fun CharacterListingItem(
    item: Result,
    onItemClick: () -> Unit
) {
    var isFav by rememberSaveable(item) {
        mutableStateOf(item.isFav)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 5.dp,
                bottom = 5.dp
            ).clickable {
              onItemClick()
            },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            Row() {
                Box {
                    NetworkImage(
                        url = item.image,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                    )
                }
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                Column {
                    Text(
                        text = item.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )
                    Text(
                        text = item.species,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.status,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(getColorForLiveStatus(item.liveStatus))
                        )
                    }
                }
            }
            FavButton(
                isFav = isFav,
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.CenterVertically)
            ) {
                isFav = !isFav
            }
        }
    }
}