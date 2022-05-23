package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems
import com.cagrisahinoglu.rickandmortycompose.util.Routes

@ExperimentalAnimationApi
@Composable
fun CharacterListingPage(
    navController: NavController,
    characterViewModel: CharacterListingViewModel
) {
    val state = characterViewModel.state
    val characterList = state.characters?.results?.toMutableStateList()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppTopBar(
            title = BottomBarItems.Characters.barItemName,
        )

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
                        ) {
                            characterViewModel.setCharacter(characterList[index])
                            navController.navigate(Routes.detail)
                        }
                    }
                }
            }
        }
    }
}