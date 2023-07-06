package com.quitr.snac.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quitr.snac.feature.discover.section.sectionRoute
import com.quitr.snac.feature.movie.movieCastRoute
import com.quitr.snac.feature.movie.movieCrewRoute
import com.quitr.snac.feature.movie.movieDetailsRoute
import com.quitr.snac.feature.movie.navigateToMovie
import com.quitr.snac.feature.movie.navigateToMovieCast
import com.quitr.snac.feature.movie.navigateToMovieCrew
import com.quitr.snac.feature.tv.episodeDetailsRoute
import com.quitr.snac.feature.tv.navigateToEpisodeDetails
import com.quitr.snac.feature.tv.tvId
import com.quitr.snac.feature.tv.tvRoute

@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navBarController: NavHostController = rememberNavController(),
) {
    val onMovieCardTap = { id: Int -> navController.navigateToMovie(id) }
    val onTvCardTap = { id: Int -> navController.navigate("Test") }
    val onPersonCardTap = { id: Int -> navController.navigate("movie") }

    NavHost(
        navController = navController, startDestination = SnacRoutes.Root.route, modifier = modifier
    ) {
        composable(SnacRoutes.Root.route) {
            HomeNavHost(rootNavController = navController, navBarController = navBarController)

        }
        sectionRoute(
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onBackPressed = { navController.popBackStack() }
        )

        movieDetailsRoute(
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onPersonCardTap = onPersonCardTap,
            onCastExpand = { movieId -> navController.navigateToMovieCast(movieId) },
            onCrewExpand = { movieId -> navController.navigateToMovieCrew(movieId) },
            onBackPressed = navController::popBackStack
        )

        movieCastRoute(
            onPersonCardTap = onPersonCardTap,
            onBackPressed = { navController.popBackStack() }
        )
        movieCrewRoute(
            onPersonCardTap = onPersonCardTap,
            onBackPressed = { navController.popBackStack() }
        )

        tvRoute(
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onPersonCardTap = onPersonCardTap,
            onEpisodeCardTap = { tvId, seasonNumber, episodeNumber ->
                navController.navigateToEpisodeDetails(tvId, seasonNumber, episodeNumber)
            },
            onSeasonCardTap = { showId, seasonNumber -> },
            onSeasonsExpand = { /*TODO*/ },
            onCastExpand = { tvId -> navController.navigate(SnacRoutes.TvCast.route(tvId)) },
            onCrewExpand = { tvId -> navController.navigate(SnacRoutes.TvCrew.route(tvId)) },
            onBackPressed = navController::popBackStack
        )

        episodeDetailsRoute(
            onPersonCardTap = onPersonCardTap,
            onGuestStarExpand = { tvId, seasonNumber, episodeNumber ->
                navController.navigateToEpisodeDetails(tvId, seasonNumber, episodeNumber)
            },
            onCrewExpand = { tvId, seasonNumber, episodeNumber ->
                navController.navigateToEpisodeDetails(tvId, seasonNumber, episodeNumber)
            },
            onBackPressed = { navBarController.popBackStack() },
        )

//
//        composable(
//            SnacRoutes.TvEpisodeCrew.route, arguments = listOf(
//                navArgument(SnacRoutes.TvEpisodeCrew.tvId) {
//                    type = NavType.IntType
//                },
//                navArgument(SnacRoutes.TvEpisodeCrew.seasonNumber) {
//                    type = NavType.IntType
//                }, navArgument(SnacRoutes.TvEpisodeCrew.episodeNumber) {
//                    type = NavType.IntType
//                }
//            )
//        ) {
//            EpisodeCrewRoute(
//                onPersonCardTapped = onPersonCardTap,
//                onBackPressed = { navController.popBackStack() })
//        }
//        composable(
//            SnacRoutes.TvEpisodeGuests.route, arguments = listOf(
//                navArgument(SnacRoutes.TvEpisodeGuests.tvId) {
//                    type = NavType.IntType
//                },
//                navArgument(SnacRoutes.TvEpisodeGuests.seasonNumber) {
//                    type = NavType.IntType
//                }, navArgument(SnacRoutes.TvEpisodeGuests.episodeNumber) {
//                    type = NavType.IntType
//                }
//            )
//        ) {
//            EpisodeGuestStarsRoute(
//                onPersonCardTapped = onPersonCardTap,
//                onBackPressed = { navController.popBackStack() })
//        }
//
//        composable(
//            SnacRoutes.TvCast.route, arguments = listOf(navArgument(SnacRoutes.TvCast.tvId) {
//                type = NavType.IntType
//            })
//        ) {
//            TvCastRoute(onPersonCardTapped = onPersonCardTap,
//                onBackPressed = { navController.popBackStack() })
//        }
//        composable(
//            SnacRoutes.TvCrew.route, arguments = listOf(navArgument(SnacRoutes.TvCrew.tvId) {
//                type = NavType.IntType
//            })
//        ) {
//            TvCrewRoute(onPersonCardTapped = onPersonCardTap,
//                onBackPressed = { navController.popBackStack() })
//        }
//
//        composable(
//            SnacRoutes.Person.route, arguments = listOf(navArgument(SnacRoutes.Person.personId) {
//                type = NavType.IntType
//            })
//        ) { backStackEntry ->
//            val personId =
//                checkNotNull(backStackEntry.arguments?.getInt(SnacRoutes.Person.personId))
//
//            PersonDetailsRoute(
//                onMovieCardTap = onMovieCardTap,
//                onTvCardTap = onTvCardTap,
//                onActingCreditsExpand = {
//                    navController.navigate(SnacRoutes.PersonCast.route(personId))
//                },
//                onOtherCreditsExpand = {
//                    navController.navigate(SnacRoutes.PersonCast.route(personId))
//                },
//                onBackPressed = { navController.popBackStack() },
//            )
//        }
//        composable(
//            SnacRoutes.PersonCast.route,
//            arguments = listOf(navArgument(SnacRoutes.PersonCast.personId) {
//                type = NavType.IntType
//            })
//        ) {
//            PersonCastRoute(onMovieCardTap = onMovieCardTap,
//                onTvCardTap = onTvCardTap,
//                onBackPressed = { navController.popBackStack() })
//        }
//        composable(
//            SnacRoutes.PersonCrew.route,
//            arguments = listOf(navArgument(SnacRoutes.PersonCrew.personId) {
//                type = NavType.IntType
//            })
//        ) {
//            PersonCrewRoute(onMovieCardTap = onMovieCardTap,
//                onTvCardTap = onTvCardTap,
//                onBackPressed = { navController.popBackStack() })
//        }
    }
}