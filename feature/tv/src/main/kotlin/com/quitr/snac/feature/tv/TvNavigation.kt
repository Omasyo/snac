package com.quitr.snac.feature.tv

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.feature.tv.details.TvCastRoute
import com.quitr.snac.feature.tv.details.TvCrewRoute
import com.quitr.snac.feature.tv.details.TvDetailsRoute
import com.quitr.snac.feature.tv.season.SeasonRoute

internal const val TvIdArg = "tv-id"
internal const val SeasonNumberArg = "season-number"
internal const val EpisodeNumberArg = "episode-number"

private object TvDetailsRoute : NavigationRoute("tv/%s") {
    override val requiredArguments: List<String>
        get() = listOf(TvIdArg)
}

private object TvCastRoute : NavigationRoute("tv/%s/cast") {
    override val requiredArguments: List<String> = listOf(TvIdArg)
}

private object TvCrewRoute : NavigationRoute("tv/%s/crew") {
    override val requiredArguments: List<String> = listOf(TvIdArg)
}

private object SeasonRoute : NavigationRoute("tv/%s/season") {
    override val requiredArguments: List<String> = listOf(TvIdArg)
}

fun NavController.navigateToTvRoute(tvId: Int, navOptions: NavOptions? = null) =
    navigate(TvDetailsRoute.route(tvId), navOptions)

fun NavController.navigateToTvCast(showId: Int, navOptions: NavOptions? = null) =
    navigate(TvCastRoute.route(showId), navOptions)

fun NavController.navigateToTvCrew(showId: Int, navOptions: NavOptions? = null) =
    navigate(TvCrewRoute.route(showId), navOptions)

fun NavController.navigateToTvSeasons(
    showId: Int,
    navOptions: NavOptions? = null
) = navigate(SeasonRoute.route(showId), navOptions)

fun NavGraphBuilder.tvRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onPersonCardTap: (id: Int) -> Unit,
    onEpisodeCardTap: (showId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onSeasonCardTap: (showId: Int, seasonNumber: Int) -> Unit,
    onSeasonsExpand: (tvId: Int) -> Unit,
    onCastExpand: (tvId: Int) -> Unit,
    onCrewExpand: (tvId: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = TvDetailsRoute.route,
    arguments = listOf(navArgument(TvIdArg) {
        type = NavType.IntType
    })
) { backStackEntry ->
    val tvId = checkNotNull(backStackEntry.arguments?.getInt(TvIdArg))

    TvDetailsRoute(
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onPersonCardTap = onPersonCardTap,
        onEpisodeCardTap = onEpisodeCardTap,
        onSeasonCardTap = onSeasonCardTap,
        onSeasonsExpand = { onSeasonsExpand(tvId) },
        onCastExpand = { onCastExpand(tvId) },
        onCrewExpand = { onCrewExpand(tvId) },
        onBackPressed = onBackPressed
    )
}

fun NavGraphBuilder.tvCastRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = TvCastRoute.route,
    arguments = listOf(
        navArgument(TvIdArg) { type = NavType.IntType }
    )
) {
    TvCastRoute(
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed
    )
}

fun NavGraphBuilder.tvCrewRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = TvCrewRoute.route,
    arguments = listOf(
        navArgument(TvIdArg) { type = NavType.IntType }
    )
) {
    TvCrewRoute(
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed
    )
}

fun NavGraphBuilder.tvSeasonRoute(
    onSeasonCardTap: (showId: Int, seasonNumber: Int) -> Unit,
    onBackPressed: () -> Unit
) = composable(
    route = SeasonRoute.route,
    arguments = listOf(
        navArgument(TvIdArg) { type = NavType.IntType },
    )
) {
    SeasonRoute(
        onSeasonCardTap = onSeasonCardTap,
        onBackPressed = onBackPressed
    )
}