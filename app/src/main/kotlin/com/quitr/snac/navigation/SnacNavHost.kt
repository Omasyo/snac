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
import com.quitr.snac.feature.people.PersonCastRoute
import com.quitr.snac.feature.people.PersonCrewRoute
import com.quitr.snac.feature.people.PersonDetailsRoute
import com.quitr.snac.feature.tv.EpisodeDetailsRoute
import com.quitr.snac.feature.tv.EpisodeCrewRoute
import com.quitr.snac.feature.tv.EpisodeGuestStarsRoute
import com.quitr.snac.feature.tv.TvCastRoute
import com.quitr.snac.feature.tv.TvCrewRoute
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
                        SnacRoutes.MovieCast.route(movieId)
                    )
                },
                onCrewExpand = {
                    navController.navigate(SnacRoutes.MovieCrew.route(movieId))
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
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            SnacRoutes.MovieCrew.route,
            arguments = listOf(navArgument(SnacRoutes.MovieCrew.movieId) {
                type = NavType.IntType
            })
        ) {
            MovieCrewRoute(
                onPersonCardTapped = onPersonCardTap,
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(
            SnacRoutes.Tv.route, arguments = listOf(navArgument(SnacRoutes.Tv.tvId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val tvId = checkNotNull(backStackEntry.arguments?.getInt(SnacRoutes.Tv.tvId))

            TvDetailsRoute(onMovieCardTap = onMovieCardTap,
                onTvCardTap = onTvCardTap,
                onPersonCardTap = onPersonCardTap,
                onEpisodeCardTap = { showId, seasonNumber, episodeNumber ->
                    navController.navigate(
                        SnacRoutes.TvEpisode.route(showId, seasonNumber, episodeNumber)
                    )
                },
                onSeasonCardTap = { showId, seasonNumber -> },
                onSeasonsExpand = { /*TODO*/ },
                onCastExpand = {
                    navController.navigate(
                        SnacRoutes.TvCast.route(tvId)
                    )
                },
                onCrewExpand = {
                    navController.navigate(
                        SnacRoutes.TvCrew.route(tvId)
                    )
                },
                onBackPressed = { navController.popBackStack() })
        }
        composable(
            SnacRoutes.TvEpisode.route, arguments = listOf(
                navArgument(SnacRoutes.TvEpisode.tvId) {
                    type = NavType.IntType
                },
                navArgument(SnacRoutes.TvEpisode.seasonNumber) {
                    type = NavType.IntType
                }, navArgument(SnacRoutes.TvEpisode.episodeNumber) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val tvId = checkNotNull(backStackEntry.arguments?.getInt(SnacRoutes.Tv.tvId))
            val seasonNumber = checkNotNull(backStackEntry.arguments?.getInt(SnacRoutes.TvEpisode.seasonNumber))
            val episodeNumber = checkNotNull(backStackEntry.arguments?.getInt(SnacRoutes.TvEpisode.episodeNumber))

            EpisodeDetailsRoute(
                onPersonCardTap = onPersonCardTap,
                onGuestStarExpand = {
                    navController.navigate(SnacRoutes.TvEpisodeGuests.route(tvId, seasonNumber, episodeNumber))
                },
                onCrewExpand = {
                    navController.navigate(SnacRoutes.TvEpisodeCrew.route(tvId, seasonNumber, episodeNumber))
                },
                onBackPressed = { navBarController.popBackStack() },
            )
        }

        composable(
            SnacRoutes.TvEpisodeCrew.route, arguments = listOf(
                navArgument(SnacRoutes.TvEpisodeCrew.tvId) {
                    type = NavType.IntType
                },
                navArgument(SnacRoutes.TvEpisodeCrew.seasonNumber) {
                    type = NavType.IntType
                }, navArgument(SnacRoutes.TvEpisodeCrew.episodeNumber) {
                    type = NavType.IntType
                }
            )
        ) {
            EpisodeCrewRoute(
                onPersonCardTapped = onPersonCardTap,
                onBackPressed = { navController.popBackStack() })
        }
        composable(
            SnacRoutes.TvEpisodeGuests.route, arguments = listOf(
                navArgument(SnacRoutes.TvEpisodeGuests.tvId) {
                    type = NavType.IntType
                },
                navArgument(SnacRoutes.TvEpisodeGuests.seasonNumber) {
                    type = NavType.IntType
                }, navArgument(SnacRoutes.TvEpisodeGuests.episodeNumber) {
                    type = NavType.IntType
                }
            )
        ) {
            EpisodeGuestStarsRoute(
                onPersonCardTapped = onPersonCardTap,
                onBackPressed = { navController.popBackStack() })
        }

        composable(
            SnacRoutes.TvCast.route, arguments = listOf(navArgument(SnacRoutes.TvCast.tvId) {
                type = NavType.IntType
            })
        ) {
            TvCastRoute(onPersonCardTapped = onPersonCardTap,
                onBackPressed = { navController.popBackStack() })
        }
        composable(
            SnacRoutes.TvCrew.route, arguments = listOf(navArgument(SnacRoutes.TvCrew.tvId) {
                type = NavType.IntType
            })
        ) {
            TvCrewRoute(onPersonCardTapped = onPersonCardTap,
                onBackPressed = { navController.popBackStack() })
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
                    navController.navigate(SnacRoutes.PersonCast.route(personId))
                },
                onOtherCreditsExpand = {
                    navController.navigate(SnacRoutes.PersonCast.route(personId))
                },
                onBackPressed = { navController.popBackStack() },
            )
        }
        composable(
            SnacRoutes.PersonCast.route,
            arguments = listOf(navArgument(SnacRoutes.PersonCast.personId) {
                type = NavType.IntType
            })
        ) {
            PersonCastRoute(onMovieCardTap = onMovieCardTap,
                onTvCardTap = onTvCardTap,
                onBackPressed = { navController.popBackStack() })
        }
        composable(
            SnacRoutes.PersonCrew.route,
            arguments = listOf(navArgument(SnacRoutes.PersonCrew.personId) {
                type = NavType.IntType
            })
        ) {
            PersonCrewRoute(onMovieCardTap = onMovieCardTap,
                onTvCardTap = onTvCardTap,
                onBackPressed = { navController.popBackStack() })
        }
    }
}