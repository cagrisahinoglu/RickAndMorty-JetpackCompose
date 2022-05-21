package com.cagrisahinoglu.rickandmortycompose.common

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cagrisahinoglu.rickandmortycompose.characterListing.CharacterListingPage
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavUtil(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarItems.Characters.route
    ){
        composable(BottomBarItems.Characters.route) { CharacterListingPage()}
    }
}