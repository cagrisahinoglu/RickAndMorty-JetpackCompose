package com.cagrisahinoglu.rickandmortycompose.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cagrisahinoglu.rickandmortycompose.util.BottomBarItems

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onItemClick: (BottomBarItems, Boolean) -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(
            bottom = 10.dp
        )
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 5.dp,
            modifier = modifier
        ) {
            val backStackEntry = navController.currentBackStackEntryAsState()
            BottomBarItems.values().forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item, selected) },
                    selectedContentColor = Color.Cyan,
                    unselectedContentColor = Color.White,
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Image(painter = painterResource(id = item.iconId), contentDescription = "")
                            Text(
                                text = item.barItemName,
                                fontSize = 12.sp
                            )
                        }
                    }
                )
            }
        }
    }

}