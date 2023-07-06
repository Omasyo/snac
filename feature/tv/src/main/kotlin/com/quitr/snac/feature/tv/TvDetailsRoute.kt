package com.quitr.snac.feature.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.NavigationRoute

const val tvId = "tv-id"
const val seasonNumberArg = "season-number"
const val episodeNumber = "episode-number"

object TvDetailsRoute : NavigationRoute("tv/%s") {
    override val requiredArguments: List<String>
        get() = listOf(tvId)
}

fun NavController.navigateToTvRoute(tvId: Int, navOptions: NavOptions? = null) =
    navigate(TvDetailsRoute.route(tvId), navOptions)

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
    arguments = listOf(navArgument(tvId) {
        type = NavType.IntType
    })
) { backStackEntry ->
    val tvId = checkNotNull(backStackEntry.arguments?.getInt(tvId))

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

@Composable
fun TvDetailsRoute(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onPersonCardTap: (id: Int) -> Unit,
    onEpisodeCardTap: (showId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onSeasonCardTap: (showId: Int, seasonNumber: Int) -> Unit,
    onSeasonsExpand: () -> Unit,
    onCastExpand: () -> Unit,
    onCrewExpand: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: TvDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.tvDetailsUiState.collectAsState()
    TvDetailsScreen(
        modifier = modifier,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onPersonCardTap = onPersonCardTap,
        onEpisodeCardTap = onEpisodeCardTap,
        onSeasonCardTap = onSeasonCardTap,
        onSeasonsExpand = onSeasonsExpand,
        onCastExpand = onCastExpand,
        onCrewExpand = onCrewExpand,
        onBackPressed = onBackPressed,
        uiState = uiState
    )
}