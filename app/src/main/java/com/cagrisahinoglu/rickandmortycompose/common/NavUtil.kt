package com.cagrisahinoglu.rickandmortycompose.common

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cagrisahinoglu.rickandmortycompose.characterDetail.CharacterDetail
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingPage
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingViewModel
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems
import com.cagrisahinoglu.rickandmortycompose.util.Routes

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavUtil(
    navHostController: NavHostController,
) {
    val characterViewModel: CharacterListingViewModel = hiltViewModel()
    NavHost(
        navController = navHostController,
        startDestination = BottomBarItems.Characters.route
    ){
        composable(BottomBarItems.Characters.route) { CharacterListingPage(
            navController = navHostController,
            characterViewModel = characterViewModel
        )}
        composable(Routes.detail) { CharacterDetail(
            navController = navHostController,
            characterViewModel = characterViewModel
        )}
    }
}