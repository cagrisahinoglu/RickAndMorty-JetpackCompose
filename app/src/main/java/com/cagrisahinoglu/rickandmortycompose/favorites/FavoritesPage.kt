package com.cagrisahinoglu.rickandmortycompose.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingItem
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar
import com.cagrisahinoglu.rickandmortycompose.common.NoResultView
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems
import com.cagrisahinoglu.rickandmortycompose.util.Routes

@Composable
fun FavoritesPage(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel
) {
    var dialogStatus by remember { mutableStateOf(false) }
    var selectedCharacterToRemove by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        favoritesViewModel.getFavoriteCharacterList()
    }
    val state = favoritesViewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (dialogStatus) {
            RemoveCharacterDialog(
                onDismissListener = {
                    dialogStatus = false
                    selectedCharacterToRemove = null
                },
                onConfirmListener = {
                    favoritesViewModel.removeFavoriteCharacter(selectedCharacterToRemove!!)
                    selectedCharacterToRemove = null
                }
            )
        }

        AppTopBar(
            title = BottomBarItems.Favorites.barItemName,
        )

        when (state) {
            is FavoritesUIState.Response -> {
                val data = state.data.collectAsLazyPagingItems()
                val loadState = data.loadState
                when {
                    loadState.source.refresh is LoadState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    data.itemCount == 0 -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            NoResultView(
                                animSize = 200.dp,
                                text = "There is no favorite character"
                            )
                        }
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            items(data.itemCount) { index ->
                                val item = data[index]
                                CharacterListingItem(
                                    item = item!!,
                                    showFavButton = false,
                                    onItemClick = {
                                        navController.navigate(Routes.detail + "/${item.id}")
                                    },
                                    onUnfavButtonClick = { character ->
                                        dialogStatus = true
                                        selectedCharacterToRemove = character
                                    }
                                )
                            }
                        }
                    }
                }
            }
            FavoritesUIState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun RemoveCharacterDialog(
    onDismissListener: () -> Unit,
    onConfirmListener: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismissListener()
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmListener()
                onDismissListener()
            }) {
                Text(text = "Remove")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissListener()
            }) {
                Text(text = "Cancel")
            }
        },
        title = { Text(text = "Remove Character") },
        text = { Text(text = "Are you sure to remove selected character?") }
    )
}