package com.quitr.snac.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.quitr.snac.core.data.SectionType
import com.quitr.snac.feature.discover.SectionRoute
import com.quitr.snac.feature.movie.MovieRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    navBarController: NavHostController = rememberAnimatedNavController(),
) {
    val innerNav = rememberAnimatedNavController()
    AnimatedNavHost(
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
                onMovieCardTap = { navController.navigate(SnacRoutes.movie(it)) },
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