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
import com.quitr.snac.feature.movie.MovieDetailsRoute
import com.quitr.snac.feature.movie.RecommendationsRoute

@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navBarController: NavHostController = rememberNavController(),
) {
    val onMovieCardTap = { id: Int -> navController.navigate(SnacRoutes.Movie.route(id)) }

    NavHost(
        navController = navController, startDestination = SnacRoutes.Root.route, modifier = modifier
    ) {
        composable(SnacRoutes.Root.route) {
            RootRoute(rootNavController = navController, navBarController = navBarController)

        }
        composable(
            SnacRoutes.Section.route,
            arguments = listOf(navArgument(SnacRoutes.Section.sectionType) {
                type = NavType.EnumType(SectionType::class.java)
            })
        ) {
            SectionRoute(onMovieCardTap = onMovieCardTap,
                onTvCardTap = {},
                onBackPressed = { navController.popBackStack() })
        }

        composable(
            SnacRoutes.Movie.route, arguments = listOf(navArgument(SnacRoutes.Movie.movieId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val movieId = checkNotNull(backStackEntry.arguments?.getInt(MovieDetailsRoute.movieId))

            MovieDetailsRoute(
                onMovieCardTap = onMovieCardTap,
                onTvCardTap = {},
                onPersonCardTap = {},
                onRecommendationsExpand = {
                    navController.navigate(
                        SnacRoutes.Recommendations.route(
                            movieId
                        )
                    )
                },
                onBackPressed = { navController.popBackStack() },
            )
        }
        composable(
            SnacRoutes.Recommendations.route,
            arguments = listOf(navArgument(SnacRoutes.Recommendations.movieId) {
                type = NavType.IntType
            })
        ) {
            RecommendationsRoute(
                onMovieCardTap = onMovieCardTap,
                onTvCardTap = {},
                onBackPressed = { navController.popBackStack() })
        }
    }
}