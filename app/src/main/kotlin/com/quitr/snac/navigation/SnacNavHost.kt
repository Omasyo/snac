package com.quitr.snac.navigation

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
import com.quitr.snac.feature.movie.MovieCastRoute
import com.quitr.snac.feature.movie.MovieCrewRoute
import com.quitr.snac.feature.people.PersonDetailsRoute
import com.quitr.snac.feature.tv.TvDetailsRoute

@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navBarController: NavHostController = rememberNavController(),
) {
    val onMovieCardTap = { id: Int -> navController.navigate(SnacRoutes.Movie.route(id)) }
    val onTvCardTap = { id: Int -> navController.navigate(SnacRoutes.Tv.route(id)) }
    val onPersonCardTap = { id: Int -> navController.navigate(SnacRoutes.Person.route(id)) }

    NavHost(
        navController = navController, startDestination = SnacRoutes.Root.route, modifier = modifier
    ) {
        composable(SnacRoutes.Root.route) {
            HomeNavHost(rootNavController = navController, navBarController = navBarController)

        }
        composable(
            SnacRoutes.Section.route,
            arguments = listOf(navArgument(SnacRoutes.Section.sectionType) {
                type = NavType.EnumType(SectionType::class.java)
            })
        ) {
            SectionRoute(onMovieCardTap = onMovieCardTap,
                onTvCardTap = onTvCardTap,
                onBackPressed = { navController.popBackStack() })
        }

        composable(
            SnacRoutes.Movie.route, arguments = listOf(navArgument(SnacRoutes.Movie.movieId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val movieId = checkNotNull(backStackEntry.arguments?.getInt(SnacRoutes.Movie.movieId))

            MovieDetailsRoute(
                onMovieCardTap = onMovieCardTap,
                onTvCardTap = onTvCardTap,
                onPersonCardTap = onPersonCardTap,
                onCastExpand = {
                    navController.navigate(
                        SnacRoutes.MovieCast.route(
                            movieId
                        )
                    )
                },
                onCrewExpand = {
                    navController.navigate(
                        SnacRoutes.MovieCrew.route(
                            movieId
                        )
                    )
                },
                onBackPressed = { navController.popBackStack() },
            )
        }
        composable(
            SnacRoutes.MovieCast.route,
            arguments = listOf(navArgument(SnacRoutes.MovieCast.movieId) {
                type = NavType.IntType
            })
        ) {
            MovieCastRoute(
                onPersonCardTapped = onPersonCardTap,
                onBackPressed = { navController.popBackStack() })
        }
        composable(
            SnacRoutes.MovieCrew.route,
            arguments = listOf(navArgument(SnacRoutes.MovieCrew.movieId) {
                type = NavType.IntType
            })
        ) {
            MovieCrewRoute(
                onPersonCardTapped = onPersonCardTap,
                onBackPressed = { navController.popBackStack() })
        }
        
        composable(
            SnacRoutes.Tv.route, arguments = listOf(navArgument(SnacRoutes.Tv.tvId) {
                type = NavType.IntType
            })
        ) {backStackEntry ->
            val tvId = checkNotNull(backStackEntry.arguments?.getInt(SnacRoutes.Tv.tvId))
            
            TvDetailsRoute(
                onMovieCardTap = onMovieCardTap,
                onTvCardTap = onTvCardTap,
                onPersonCardTap = onPersonCardTap,
                onEpisodeCardTap = {showId, seasonNumber, episodeNumber ->  },
                onSeasonCardTap = {showId, seasonNumber ->  },
                onSeasonsExpand = { /*TODO*/ },
                onCastExpand = { /*TODO*/ },
                onCrewExpand = { /*TODO*/ },
                onBackPressed = { /*TODO*/ })
        }

        composable(
            SnacRoutes.Person.route, arguments = listOf(navArgument(SnacRoutes.Person.personId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val personId =
                checkNotNull(backStackEntry.arguments?.getInt(SnacRoutes.Person.personId))

            PersonDetailsRoute(
                onMovieCardTap = onMovieCardTap,
                onTvCardTap = onTvCardTap,
                onActingCreditsExpand = {
                },
                onOtherCreditsExpand = {
//                    navController.navigate(
//                        SnacRoutes.MovieCrew.route(
//                            movieId
//                        )
//                    )
                },
                onBackPressed = { navController.popBackStack() },
            )
        }
    }
}