package com.cagrisahinoglu.rickandmortycompose.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.rickandmortycompose.R
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingItem
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar
import com.cagrisahinoglu.rickandmortycompose.common.NoResultView
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems
import com.cagrisahinoglu.rickandmortycompose.util.ViewState

@Composable
fun FavoritesPage(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel
) {
    val viewState = favoritesViewModel.viewState.value
    var dialogStatus by remember { mutableStateOf(false) }
    var selectedCharacterToRemove by remember { mutableStateOf<Character?>(null) }
//    LaunchedEffect(Unit) {
//        favoritesViewModel.getFavoriteCharacterList()
//    }
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

        when (viewState) {
            is ViewState.Success -> {
                val data = viewState.data
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(data.size) { index ->
                        val item = data[index]
                        CharacterListingItem(
                            item = item,
                            showFavButton = false,
                            onItemClick = {
//                                characterViewModel.setCharacter(item)
//                                navController.navigate(Routes.detail)
                            },
                            onUnfavButtonClick = {
                                dialogStatus = true
                                selectedCharacterToRemove = it
                            }
                        )
                    }
                }
            }
            ViewState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            ViewState.NoResult -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    NoResultView(
                        animSize = 200.dp,
                        animFileRes = R.raw.no_result_lottie,
                        text = "There is no favorite character"
                    )
                }
            }
            is ViewState.Error -> {

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