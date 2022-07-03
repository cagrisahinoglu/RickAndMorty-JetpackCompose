package com.cagrisahinoglu.rickandmortycompose.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingItem
import com.cagrisahinoglu.rickandmortycompose.common.AppTopBar
import com.cagrisahinoglu.rickandmortycompose.common.NoResultView
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarNames
import com.cagrisahinoglu.rickandmortycompose.util.Routes
import com.cagrisahinoglu.rickandmortycompose.util.ViewState

@Composable
fun SearchPage(
    navController: NavController,
    searchPageViewModel: SearchPageViewModel
) {
    val viewState = searchPageViewModel.uiState.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTopBar(title = BottomBarNames.search)
        Column {
            TextField(
                value = searchPageViewModel.searchText,
                onValueChange = {
                    searchPageViewModel.searchText = it
                    searchPageViewModel.searchCharacter()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .padding(10.dp),
                placeholder = { Text(text = "Type character name") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "remove",
                        Modifier.clickable {
                            searchPageViewModel.searchText = ""
                            searchPageViewModel.searchCharacter()
                        })
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            when (viewState) {
                SearchPageUIState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                SearchPageUIState.MissingCharacter -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        NoResultView(
                            animSize = 200.dp,
                            text = "Please enter at least 2 character to search"
                        )
                    }
                }
                is SearchPageUIState.Success -> {
                    val data = viewState.data.collectAsLazyPagingItems()
                    val hasData = data.itemCount > 0
                    if (hasData) {
                        LazyColumn() {
                            items(data.itemCount) { index ->
                                val character = data[index]
                                var isFav by rememberSaveable(character) {
                                    mutableStateOf(character?.isFav)
                                }
                                CharacterListingItem(
                                    item = character!!,
                                    onItemClick = {
                                        navController.navigate(Routes.detail + "/${character.id}")
                                    },
                                    isFav = isFav!!,
                                    onFavButtonClick = {
                                        searchPageViewModel.updateFavStatus(
                                            character = character,
                                            isFav = isFav!!
                                        )
                                        isFav = !isFav!!
                                    }
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            NoResultView(
                                animSize = 200.dp,
                                text = "There is no result. Please search something else"
                            )
                        }
                    }
                }
                is SearchPageUIState.Error -> TODO()
            }
        }
    }
}