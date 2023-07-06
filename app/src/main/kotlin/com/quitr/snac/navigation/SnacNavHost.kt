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
import com.quitr.snac.feature.people.navigateToPerson
import com.quitr.snac.feature.people.personCastRoute
import com.quitr.snac.feature.people.personCrewRoute
import com.quitr.snac.feature.people.personRoute
import com.quitr.snac.feature.tv.episodeCrewRoute
import com.quitr.snac.feature.tv.episodeDetailsRoute
import com.quitr.snac.feature.tv.episodeGuestsRoute
import com.quitr.snac.feature.tv.navigateToEpisodeCrew
import com.quitr.snac.feature.tv.navigateToEpisodeDetails
import com.quitr.snac.feature.tv.navigateToEpisodeGuests
import com.quitr.snac.feature.tv.navigateToTvRoute
import com.quitr.snac.feature.tv.navigateTvCast
import com.quitr.snac.feature.tv.navigateTvCrew
import com.quitr.snac.feature.tv.tvCastRoute
import com.quitr.snac.feature.tv.tvCrewRoute
import com.quitr.snac.feature.tv.tvId
import com.quitr.snac.feature.tv.tvRoute

@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navBarController: NavHostController = rememberNavController(),
) {
    val onMovieCardTap = { id: Int -> navController.navigateToMovie(id) }
    val onTvCardTap = { id: Int -> navController.navigateToTvRoute(id) }
    val onPersonCardTap = { id: Int -> navController.navigateToPerson(id) }

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
            onSeasonCardTap = { showId, seasonNumber -> },
            onSeasonsExpand = { /*TODO*/ },
            onCastExpand = { tvId -> navController.navigateTvCast(tvId) },
            onCrewExpand = { tvId -> navController.navigateTvCrew(tvId) },
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
        
        personRoute(
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onActingCreditsExpand = { personId ->
                navController.navigateTvCast(personId)
            },
            onOtherCreditsExpand = { personId ->
                navController.navigateTvCrew(personId)
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