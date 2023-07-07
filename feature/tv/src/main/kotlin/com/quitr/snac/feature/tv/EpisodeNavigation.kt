package com.quitr.snac.feature.tv

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.feature.tv.episode.EpisodeCrewRoute
import com.quitr.snac.feature.tv.episode.EpisodeDetailsRoute
import com.quitr.snac.feature.tv.episode.EpisodeGuestStarsRoute
import com.quitr.snac.feature.tv.episode.EpisodeRoute

private object EpisodeRoute : NavigationRoute("tv/%s/season/%s/episode") {
    override val requiredArguments: List<String> = listOf(TvIdArg, SeasonNumberArg)
}

private object EpisodeDetailsRoute : NavigationRoute("tv/%s/season/%s/episode/%s") {
    override val requiredArguments: List<String> = listOf(
        TvIdArg, SeasonNumberArg, EpisodeNumberArg
    )
}

private object EpisodeCrewRoute : NavigationRoute("tv/%s/season/%s/episode/%s/crew") {
    override val requiredArguments: List<String> = listOf(
        TvIdArg, SeasonNumberArg, EpisodeNumberArg
    )
}

private object EpisodeGuestsRoute : NavigationRoute("tv/%s/season/%s/episode/%s/guests") {
    override val requiredArguments: List<String> = listOf(
        TvIdArg, SeasonNumberArg, EpisodeNumberArg
    )
}

fun NavController.navigateToTvEpisodes(showId: Int, seasonNumber: Int) =
    navigate(EpisodeRoute.route(showId, seasonNumber))

fun NavController.navigateToEpisodeDetails(showId: Int, seasonNumber: Int, episodeNumber: Int) =
    navigate(EpisodeDetailsRoute.route(showId, seasonNumber, episodeNumber))

fun NavController.navigateToEpisodeCrew(showId: Int, seasonNumber: Int, episodeNumber: Int) =
    navigate(EpisodeCrewRoute.route(showId, seasonNumber, episodeNumber))

fun NavController.navigateToEpisodeGuests(showId: Int, seasonNumber: Int, episodeNumber: Int) =
    navigate(EpisodeGuestsRoute.route(showId, seasonNumber, episodeNumber))

fun NavGraphBuilder.episodeRoute(
    onEpisodeTap: (tvId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = EpisodeRoute.route,
    arguments = listOf(
        navArgument(TvIdArg) { type = NavType.IntType },
        navArgument(SeasonNumberArg) { type = NavType.IntType }
    )
) {
    EpisodeRoute(
        onEpisodeTap = onEpisodeTap,
        onBackPressed = onBackPressed
    )
}

fun NavGraphBuilder.episodeDetailsRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onGuestStarExpand: (tvId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onCrewExpand: (tvId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = EpisodeDetailsRoute.route,
    arguments = listOf(
        navArgument(TvIdArg) { type = NavType.IntType },
        navArgument(SeasonNumberArg) { type = NavType.IntType },
        navArgument(EpisodeNumberArg) { type = NavType.IntType }
    )
) { backStackEntry ->
    val tvId = checkNotNull(backStackEntry.arguments?.getInt(TvIdArg))
    val seasonNumber = checkNotNull(backStackEntry.arguments?.getInt(SeasonNumberArg))
    val episodeNumber = checkNotNull(backStackEntry.arguments?.getInt(EpisodeNumberArg))

    EpisodeDetailsRoute(
        episodeNumber = episodeNumber,
        onPersonCardTap = onPersonCardTap,
        onGuestStarExpand = { onGuestStarExpand(tvId, seasonNumber, episodeNumber) },
        onCrewExpand = { onCrewExpand(tvId, seasonNumber, episodeNumber) },
        onBackPressed = onBackPressed
    )
}

fun NavGraphBuilder.episodeCrewRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = EpisodeCrewRoute.route,
    arguments = listOf(
        navArgument(TvIdArg) { type = NavType.IntType },
        navArgument(SeasonNumberArg) { type = NavType.IntType },
        navArgument(EpisodeNumberArg) { type = NavType.IntType })
) { backStackEntry ->
    val episodeNumber = checkNotNull(backStackEntry.arguments?.getInt(EpisodeNumberArg))

    EpisodeCrewRoute(
        episodeNumber = episodeNumber,
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed
    )
}


fun NavGraphBuilder.episodeGuestsRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = EpisodeGuestsRoute.route,
    arguments = listOf(
        navArgument(TvIdArg) { type = NavType.IntType },
        navArgument(SeasonNumberArg) { type = NavType.IntType },
        navArgument(EpisodeNumberArg) { type = NavType.IntType })
) { backStackEntry ->
    val episodeNumber = checkNotNull(backStackEntry.arguments?.getInt(EpisodeNumberArg))

    EpisodeGuestStarsRoute(
        episodeNumber = episodeNumber,
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed
    )
}
