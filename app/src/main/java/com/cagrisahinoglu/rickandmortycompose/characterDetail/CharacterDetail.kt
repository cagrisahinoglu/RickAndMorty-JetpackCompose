package com.cagrisahinoglu.rickandmortycompose.characterDetail

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingViewModel
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar
import com.cagrisahinoglu.rickandmortycompose.common.NetworkImage
import com.cagrisahinoglu.rickandmortycompose.common.NoResultView
import com.cagrisahinoglu.rickandmortycompose.util.ViewState
import com.cagrisahinoglu.rickandmortycompose.util.getColorForLiveStatus

@Composable
fun CharacterDetail(
    navController: NavController,
    characterDetailViewModel: CharacterDetailViewModel,
    characterId: Int
) {
    LaunchedEffect(characterId) {
        characterDetailViewModel.getCharacterDetail(characterId)
    }
    val viewState = characterDetailViewModel.viewState.value
    when (viewState) {
        is ViewState.Success -> {
            val character = viewState.data
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                AppTopBar(
                    title = character.name,
                    startIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .clickable {
                                    navController.popBackStack()
                                })
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NetworkImage(
                        url = character.image,
                        modifier = Modifier
                            .size(180.dp)
                            .clip(
                                RoundedCornerShape(20.dp)
                            )
                            .border(
                                width = 5.dp,
                                color = getColorForLiveStatus(character.status),
                                shape = RoundedCornerShape(20.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Column(
                            modifier = Modifier.padding(15.dp)
                        ) {
                            CharacterDetailText(
                                attributeTitle = "Name",
                                attribute = character.name
                            )
                            CharacterDetailText(
                                attributeTitle = "Gender",
                                attribute = character.gender
                            )
                            CharacterDetailText(
                                attributeTitle = "Species",
                                attribute = character.species
                            )
                            CharacterDetailText(
                                attributeTitle = "Status",
                                attribute = character.status
                            )
                        }
                    }
                }
            }
        }
        ViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        ViewState.NoResult -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                NoResultView(animSize = 200.dp, text = "Character not found")
            }
        }
        is ViewState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                NoResultView(animSize = 200.dp, text = "Character not found")
            }
        }
    }
}

@Composable
fun CharacterDetailText(
    attributeTitle: String,
    attribute: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$attributeTitle: ",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = attribute,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary
        )
    }
    Divider(
        thickness = 1.dp,
        color = Color.Gray.copy(alpha = 0.4f)
    )
}