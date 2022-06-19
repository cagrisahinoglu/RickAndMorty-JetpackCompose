package com.cagrisahinoglu.rickandmortycompose.characterListing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems
import com.cagrisahinoglu.rickandmortycompose.util.Routes

@Composable
fun CharacterListingPage(
    navController: NavController,
    characterViewModel: CharacterListingViewModel
) {
    val viewState = characterViewModel.viewState.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppTopBar(
            title = BottomBarItems.Characters.barItemName,
        )

        when(viewState) {
            is CharacterListingViewState.Success -> {
                val pagingItems = viewState.data.collectAsLazyPagingItems()
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(pagingItems.itemCount) { index ->
                        val item = pagingItems[index]
                        var isFav by rememberSaveable(pagingItems[index]) {
                            mutableStateOf(pagingItems[index]?.isFav)
                        }
                        CharacterListingItem(
                            item = item!!,
                            isFav = isFav!!,
                            onItemClick = {
                                characterViewModel.setCharacter(item)
                                navController.navigate(Routes.detail)
                            },
                            onFavButtonClick = {
                                characterViewModel.updateFavStatus(
                                    character = item,
                                    isFav = isFav!!
                                )
                                isFav = !isFav!!
                            }
                        )
                    }
                }
            }
            is CharacterListingViewState.Loading ->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            is CharacterListingViewState.Error -> {

            }
        }
    }
}