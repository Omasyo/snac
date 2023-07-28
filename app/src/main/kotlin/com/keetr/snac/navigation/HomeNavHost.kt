package com.keetr.snac.navigation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.keetr.snac.feature.discover.discover.discoverRoute
import com.keetr.snac.feature.discover.section.navigateToSection
import com.keetr.snac.feature.movie.navigateToMovieDetails
import com.keetr.snac.feature.people.navigateToPersonDetails
import com.keetr.snac.feature.search.SearchViewModel
import com.keetr.snac.feature.search.SnacSearch
import com.keetr.snac.feature.search.SnacSearchBar
import com.keetr.snac.feature.tv.navigateToTvRoute

@Composable
fun HomeNavHost(
    rootNavController: NavHostController,
    navBarController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier,
        topBar = {
            SnacSearch(
                modifier = Modifier.testTag("search_bar"),
                onMovieCardTap = { id -> rootNavController.navigateToMovieDetails(id) },
                onTvCardTap = { id -> rootNavController.navigateToTvRoute(id) },
                onPersonCardTap = { id -> rootNavController.navigateToPersonDetails(id) }
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navBarController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                navBarItems.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navBarController.navigate(screen.route) {
                                popUpTo(navBarController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
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
            startDestination = "discover",
            modifier = Modifier.padding(innerPadding)
        ) {
            discoverRoute(

                onCarouselExpand = { sectionType ->
                    rootNavController.navigateToSection(sectionType)
                },
                onTvCardClicked = { id ->
                    rootNavController.navigateToTvRoute(id)
                },
                onMovieCardClicked = { id ->
                    rootNavController.navigateToMovieDetails(id)
                },
            )


            composable("Library") {
                Box(Modifier.fillMaxSize()) {
                    Text("Constructing", Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

private val navBarItems = NavBarScreens.values()