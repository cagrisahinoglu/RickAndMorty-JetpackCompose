package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar

@ExperimentalAnimationApi
@Composable
fun CharacterListingPage(
    characterListingViewModel: CharacterListingViewModel = hiltViewModel()
) {
    val state = characterListingViewModel.state
    val characterList = state.characters?.results?.toMutableStateList()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppTopBar {
            Text(
                text = "Characters",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.body1,
            )
        }

        AnimatedVisibility(
            visible = state.isLoading
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        AnimatedVisibility(
            visible = !state.isLoading
                    && state.errorMessage.isEmpty()
                    && state.characters != null
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                characterList?.size?.let {
                    items(it) { index ->
                        CharacterListingItem(
                            item = characterList[index]
                        )
                    }
                }
            }
        }
    }
}