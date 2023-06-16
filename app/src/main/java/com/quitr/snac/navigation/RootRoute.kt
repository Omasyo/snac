package com.quitr.snac.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.quitr.snac.feature.home.HomeRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootRoute(
    rootNavController: NavHostController,
    navBarController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier,
        bottomBar = {
            var selected by remember {
                mutableStateOf(0)
            }
            NavigationBar() {

//                val navBackStackEntry by navBarController.currentBackStackEntryAsState()
//                val currentDestination = navBackStackEntry?.destination

                repeat(3) {
                    NavigationBarItem(
                        selected = it == selected,
                        onClick = { selected = it },
                        icon = { Icon(Icons.Outlined.Home, null) }
                    )
                }
            }
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navBarController,
            startDestination = SnacRoutes.home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(SnacRoutes.home) {
                HomeRoute(
                    onSectionClicked = { sectionType ->
                        rootNavController.navigate(SnacRoutes.section(sectionType))
                    },
                    onTvCardClicked = { },
                    onMovieCardClicked = { },
                )
            }
        }
    }
}