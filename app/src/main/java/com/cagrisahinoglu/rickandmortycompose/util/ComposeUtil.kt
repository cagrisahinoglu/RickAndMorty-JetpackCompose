package com.cagrisahinoglu.rickandmortycompose.util

fun getBottomBarVisibleStatus(
    currentDestination: String
): Boolean {
    return currentDestination == Routes.characters
            || currentDestination == Routes.favorites
            || currentDestination == Routes.search
}
