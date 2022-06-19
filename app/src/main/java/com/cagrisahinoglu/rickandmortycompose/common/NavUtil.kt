package com.cagrisahinoglu.rickandmortycompose.common

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cagrisahinoglu.rickandmortycompose.characterDetail.CharacterDetail
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingPage
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingViewModel
import com.cagrisahinoglu.rickandmortycompose.favorites.FavoritesPage
import com.cagrisahinoglu.rickandmortycompose.favorites.FavoritesViewModel
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems
import com.cagrisahinoglu.rickandmortycompose.util.Routes

@Composable
fun NavUtil(
    navHostController: NavHostController,
) {
    val characterViewModel: CharacterListingViewModel = hiltViewModel()
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
    NavHost(
        navController = navHostController,
        startDestination = BottomBarItems.Characters.route
    ) {
        composable(BottomBarItems.Characters.route) {
            CharacterListingPage(
                navController = navHostController,
                characterViewModel = characterViewModel
            )
        }
        composable(BottomBarItems.Favorites.route) {
            FavoritesPage(
                navController = navHostController,
                favoritesViewModel = favoritesViewModel
            )
        }
        composable(Routes.detail) {
            CharacterDetail(
                navController = navHostController,
                characterViewModel = characterViewModel
            )
        }
    }
}