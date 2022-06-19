package com.cagrisahinoglu.rickandmortycompose.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingItem
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems
import com.cagrisahinoglu.rickandmortycompose.util.ViewState

@Composable
fun FavoritesPage(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel
) {
    val viewState = favoritesViewModel.viewState.value
    LaunchedEffect(Unit) {
        favoritesViewModel.getFavoriteCharacterList()
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                                favoritesViewModel.removeFavoriteCharacter(it)
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
                Box(modifier = Modifier.fillMaxSize()) {

                }
            }
            is ViewState.Error -> {

            }

        }
    }
}