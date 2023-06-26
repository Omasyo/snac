package com.quitr.snac.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.quitr.snac.feature.discover.discover.DiscoverRoute

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
            NavigationBar() {

                val navBackStackEntry by navBarController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                navBarItems.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navBarController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navBarController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                if (selected) screen.selectedIcon else screen.unSelectedIcon,
                                null
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navBarController,
            startDestination = SnacRoutes.discover,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                SnacRoutes.discover,
            ) {
                DiscoverRoute(
                    onSectionClicked = { sectionType ->
                        rootNavController.navigate(SnacRoutes.section(sectionType))
                    },
                    onTvCardClicked = { },
                    onMovieCardClicked = { id -> rootNavController.navigate(SnacRoutes.movie(id)) },
                )
            }

            composable(SnacRoutes.library) {
                Box(Modifier.fillMaxSize()) {
                    Text("Constructing", Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

//@OptIn(ExperimentalAnimationApi::class)
//internal fun NavGraphBuilder.navBarComposable(
//    route: String,
//    arguments: List<NamedNavArgument> = emptyList(),
//    content: @Composable() (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit),
//) = composable(
//    route,
//    arguments,
////    enterTransition = { EnterTransition.None },
////    exitTransition = { ExitTransition.None },
//    content = content
//)

private val navBarItems = NavBarScreens.values()