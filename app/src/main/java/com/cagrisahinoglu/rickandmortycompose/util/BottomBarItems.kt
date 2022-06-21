package com.cagrisahinoglu.rickandmortycompose.util

import com.cagrisahinoglu.rickandmortycompose.R

enum class BottomBarItems(
    val route: String,
    val barItemName: String,
    val iconId: Int
) {
    Characters(
        Routes.characters,
        BottomBarNames.characters,
        R.drawable.ic_characters
    ),
    Favorites(
        Routes.favorites,
        BottomBarNames.favorites,
        R.drawable.ic_favorites
    ),
    Search(
        Routes.search,
        BottomBarNames.search,
        R.drawable.ic_search
    )

}
