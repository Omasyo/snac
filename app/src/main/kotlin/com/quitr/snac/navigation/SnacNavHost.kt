package com.quitr.snac.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.feature.discover.section.SectionRoute
import com.quitr.snac.feature.movie.MovieRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navBarController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = SnacRoutes.root,
        modifier = modifier
    ) {
        composable(SnacRoutes.root) {
            RootRoute(rootNavController = navController, navBarController = navBarController)

        }
        composable(
            SnacRoutes.section,
            arguments = listOf(navArgument(SnacRoutes.sectionArg) {
                type = NavType.EnumType(SectionType::class.java)
            })
        ) { backStackEntry ->

            val sectionType = backStackEntry.arguments?.get(SnacRoutes.sectionArg) as SectionType
            SectionRoute(
                sectionType = sectionType,
                onMovieCardTap = { id -> navController.navigate(SnacRoutes.movie(id)) },
                onTvCardTap = {},
                onBackPressed = { navController.popBackStack() })
        }

        composable(
            SnacRoutes.movie,
            arguments = listOf(navArgument(SnacRoutes.movieArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val sectionType = backStackEntry.arguments!!.getInt(SnacRoutes.sectionArg)
            MovieRoute()
        }
    }
}