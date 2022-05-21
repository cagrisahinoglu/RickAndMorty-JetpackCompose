package com.cagrisahinoglu.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cagrisahinoglu.rickandmortycompose.common.BottomBar
import com.cagrisahinoglu.rickandmortycompose.common.NavUtil
import com.cagrisahinoglu.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomBar(
                                navController = navController,
                                onItemClick = { item, isSelected ->
                                    if(!isSelected) {
                                        navController.navigate(item.route)
                                    }
                                }
                            )
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