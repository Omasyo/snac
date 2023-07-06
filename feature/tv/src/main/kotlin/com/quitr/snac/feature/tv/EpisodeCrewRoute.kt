package com.quitr.snac.feature.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.ui.show.ShowCreditsScreen


object EpisodeCrewRoute : NavigationRoute("tv/%s/season/%s/episode/%s/crew") {
    override val requiredArguments: List<String> = listOf(
        tvId,
        seasonNumberArg,
        episodeNumber
    )
}

fun NavController.navigateToEpisodeCrew(showId: Int, seasonNumber: Int, episodeNumber: Int) =
    navigate(EpisodeDetailsRoute.route(showId, seasonNumber, episodeNumber))

fun NavGraphBuilder.episodeCrewRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = EpisodeCrewRoute.route,
    arguments = listOf(
        navArgument(tvId) { type = NavType.IntType },
        navArgument(seasonNumberArg) { type = NavType.IntType },
        navArgument(episodeNumber) { type = NavType.IntType }
    )
) {
    EpisodeCrewRoute(
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed
    )
}

@Composable
fun EpisodeCrewRoute(
    modifier: Modifier = Modifier,
    onPersonCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SeasonScreenViewModel = hiltViewModel()
) {
    val state = viewModel.seasonScreenUiState.collectAsState().value
    if (state is SeasonScreenUiState.Success) {
        ShowCreditsScreen(
            modifier,
            title = stringResource(R.string.crew),
            onPersonCardTap = onPersonCardTap,
            onBackPressed = onBackPressed,
            people = state.season.episodes.first().crew
        )
    }
}