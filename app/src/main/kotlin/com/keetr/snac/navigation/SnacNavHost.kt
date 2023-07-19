package com.keetr.snac.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keetr.snac.feature.discover.section.sectionRoute
import com.keetr.snac.feature.movie.movieCastRoute
import com.keetr.snac.feature.movie.movieCrewRoute
import com.keetr.snac.feature.movie.movieDetailsRoute
import com.keetr.snac.feature.movie.navigateToMovieCast
import com.keetr.snac.feature.movie.navigateToMovieCrew
import com.keetr.snac.feature.movie.navigateToMovieDetails
import com.keetr.snac.feature.people.navigateToPersonCast
import com.keetr.snac.feature.people.navigateToPersonCrew
import com.keetr.snac.feature.people.navigateToPersonDetails
import com.keetr.snac.feature.people.personCastRoute
import com.keetr.snac.feature.people.personCrewRoute
import com.keetr.snac.feature.people.personDetailsRoute
import com.keetr.snac.feature.tv.episodeCrewRoute
import com.keetr.snac.feature.tv.episodeDetailsRoute
import com.keetr.snac.feature.tv.episodeGuestsRoute
import com.keetr.snac.feature.tv.episodeRoute
import com.keetr.snac.feature.tv.navigateToEpisodeCrew
import com.keetr.snac.feature.tv.navigateToEpisodeDetails
import com.keetr.snac.feature.tv.navigateToEpisodeGuests
import com.keetr.snac.feature.tv.navigateToTvCast
import com.keetr.snac.feature.tv.navigateToTvCrew
import com.keetr.snac.feature.tv.navigateToTvEpisodes
import com.keetr.snac.feature.tv.navigateToTvRoute
import com.keetr.snac.feature.tv.navigateToTvSeasons
import com.keetr.snac.feature.tv.tvCastRoute
import com.keetr.snac.feature.tv.tvCrewRoute
import com.keetr.snac.feature.tv.tvRoute
import com.keetr.snac.feature.tv.tvSeasonRoute

@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navBarController: NavHostController = rememberNavController(),
) {
    val onMovieCardTap = { id: Int -> navController.navigateToMovieDetails(id) }
    val onTvCardTap = { id: Int -> navController.navigateToTvRoute(id) }
    val onPersonCardTap = { id: Int -> navController.navigateToPersonDetails(id) }

    NavHost(
        navController = navController, startDestination = SnacRoutes.Root.route, modifier = modifier
    ) {
        composable(SnacRoutes.Root.route) {
            HomeNavHost(rootNavController = navController, navBarController = navBarController)

        }
        sectionRoute(onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onBackPressed = navController::popBackStack)

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
            onBackPressed = navController::popBackStack
        )
        movieCrewRoute(
            onPersonCardTap = onPersonCardTap,
            onBackPressed = navController::popBackStack
        )

        tvRoute(
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onPersonCardTap = onPersonCardTap,
            onEpisodeCardTap = { tvId, seasonNumber, episodeNumber ->
                navController.navigateToEpisodeDetails(tvId, seasonNumber, episodeNumber)
            },
            onSeasonCardTap = { tvId, seasonNumber -> navController.navigateToTvEpisodes(tvId, seasonNumber) },
            onSeasonsExpand = { tvId -> navController.navigateToTvSeasons(tvId) },
            onCastExpand = { tvId -> navController.navigateToTvCast(tvId) },
            onCrewExpand = { tvId -> navController.navigateToTvCrew(tvId) },
            onBackPressed = navController::popBackStack
        )
        tvCastRoute(
            onPersonCardTap = onPersonCardTap,
            onBackPressed = navController::popBackStack
        )
        tvCrewRoute(
            onPersonCardTap = onPersonCardTap,
            onBackPressed = navController::popBackStack
        )
        tvSeasonRoute(
            onSeasonCardTap = { tvId, seasonNumber -> navController.navigateToTvEpisodes(tvId, seasonNumber) },
            onBackPressed = navController::popBackStack
        )

        episodeRoute(
            onEpisodeTap = { tvId, seasonNumber, episodeNumber ->
                           navController.navigateToEpisodeDetails(tvId,seasonNumber, episodeNumber)
            },
            onBackPressed = navController::popBackStack
        )
        episodeDetailsRoute(
            onPersonCardTap = onPersonCardTap,
            onGuestStarExpand = { tvId, seasonNumber, episodeNumber ->
                navController.navigateToEpisodeGuests(tvId, seasonNumber, episodeNumber)
            },
            onCrewExpand = { tvId, seasonNumber, episodeNumber ->
                navController.navigateToEpisodeCrew(tvId, seasonNumber, episodeNumber)
            },
            onBackPressed = { navBarController.popBackStack() },
        )
        episodeCrewRoute(
            onPersonCardTap = onPersonCardTap,
            onBackPressed = navController::popBackStack
        )
        episodeGuestsRoute(
            onPersonCardTap = onPersonCardTap,
            onBackPressed = navController::popBackStack
        )
        
        personDetailsRoute(
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onActingCreditsExpand = { personId ->
                navController.navigateToPersonCast(personId)
            },
            onOtherCreditsExpand = { personId ->
                navController.navigateToPersonCrew(personId)
            },
            onBackPressed = navController::popBackStack,
        )


        personCastRoute(onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onBackPressed = navController::popBackStack)

        personCrewRoute(onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onBackPressed = navController::popBackStack)

    }
}