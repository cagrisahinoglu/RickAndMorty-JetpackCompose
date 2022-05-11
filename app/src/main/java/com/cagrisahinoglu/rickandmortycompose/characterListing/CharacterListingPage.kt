package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalAnimationApi
@Composable
fun CharacterListingPage(
    characterListingViewModel: CharacterListingViewModel = viewModel()
) {
    val state = characterListingViewModel.state
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
            state.characters?.results?.size?.let {
                items(it) { index ->
                    CharacterListingItem(
                        item = state.characters!!.results[index]
                    )
                }
            }
        }
    }
}