package com.cagrisahinoglu.rickandmortycompose.characterListing

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems
import com.cagrisahinoglu.rickandmortycompose.util.Routes
import com.cagrisahinoglu.rickandmortycompose.util.ViewState

@Composable
fun CharacterListingPage(
    navController: NavController,
    characterViewModel: CharacterListingViewModel
) {
    val viewState = characterViewModel.uiState.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppTopBar(
            title = BottomBarItems.Characters.barItemName,
        )

        when (viewState) {
            is CharacterListingUIState.Response -> {
                val pagingItems = viewState.data.collectAsLazyPagingItems()
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(pagingItems.itemCount) { index ->
                        val item = pagingItems[index]
                        var isFav by rememberSaveable(pagingItems[index]) {
                            mutableStateOf(pagingItems[index]?.isFav)
                        }
                        item?.let { character ->
                            isFav?.let { fav ->
                                CharacterListingItem(
                                    item = character,
                                    isFav = fav,
                                    onItemClick = {
                                        navController.navigate(Routes.detail + "/${item.id}")
                                    },
                                    onFavButtonClick = {
                                        characterViewModel.updateFavStatus(
                                            character = item,
                                            isFav = fav
                                        )
                                        isFav = !fav
                                    }
                                )
                            }
                        }
                    }
                }
            }
            is CharacterListingUIState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}