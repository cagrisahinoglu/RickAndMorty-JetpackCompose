package com.cagrisahinoglu.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cagrisahinoglu.rickandmortycompose.common.BottomBar
import com.cagrisahinoglu.rickandmortycompose.common.NavUtil
import com.cagrisahinoglu.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import com.cagrisahinoglu.rickandmortycompose.util.getBottomBarVisibleStatus
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(
                                visible = getBottomBarVisibleStatus(
                                    currentDestination = backStackEntry?.destination?.route ?: ""
                                ),
                                enter = slideInVertically(initialOffsetY = { it }),
                                exit = slideOutVertically(targetOffsetY = { it }),
                            ) {
                                BottomBar(
                                    navController = navController,
                                    onItemClick = { item, isSelected ->
                                        if(!isSelected) {
                                            navController.navigate(item.route)
                                        }
                                    }
                                )
                            }
                        }
                    ){
                        Box(modifier = Modifier.padding(it)) {
                            NavUtil(navHostController = navController)
                        }
                    }
                }
            }
        }
    }
}