package com.quitr.snac.feature.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.EpisodeDetails
import com.quitr.snac.core.model.NavigationRoute

object EpisodeDetailsRoute : NavigationRoute("tv/%s/season/%s/episode/%s") {
    override val requiredArguments: List<String> = listOf(tvId, seasonNumber, episodeNumber)
}

fun NavController.navigateToEpisodeDetails(showId: Int, seasonNumber: Int, episodeNumber: Int) =
    navigate(EpisodeDetailsRoute.route(showId, seasonNumber, episodeNumber))

fun NavGraphBuilder.episodeDetailsRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onGuestStarExpand: (tvId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onCrewExpand: (tvId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = EpisodeDetailsRoute.route,
    arguments = listOf(
        navArgument(tvId) { type = NavType.IntType },
        navArgument(seasonNumber) { type = NavType.IntType },
        navArgument(episodeNumber) { type = NavType.IntType }
    )
) { backStackEntry ->
    val tvId = checkNotNull(backStackEntry.arguments?.getInt(tvId))
    val seasonNumber = checkNotNull(backStackEntry.arguments?.getInt(seasonNumber))
    val episodeNumber = checkNotNull(backStackEntry.arguments?.getInt(episodeNumber))

    EpisodeDetailsRoute(
        onPersonCardTap = onPersonCardTap,
        onGuestStarExpand = { onGuestStarExpand(tvId, seasonNumber, episodeNumber) },
        onCrewExpand = { onCrewExpand(tvId, seasonNumber, episodeNumber) },
        onBackPressed = onBackPressed
    )
}


@Composable
fun EpisodeDetailsRoute(
    modifier: Modifier = Modifier,
    onPersonCardTap: (id: Int) -> Unit,
    onGuestStarExpand: () -> Unit,
    onCrewExpand: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SeasonScreenViewModel = hiltViewModel(),
) {
    when (val uiState = viewModel.seasonScreenUiState.collectAsState().value) {
        is SeasonScreenUiState.Error -> {
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(16f.dp)
                    .padding(top = 36f.dp)
            ) {
                Text(
                    uiState.message, style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        SeasonScreenUiState.Loading -> EpisodeScreenPlaceholder(onBackPressed = onBackPressed)
        is SeasonScreenUiState.Success -> {
            EpisodeDetailsScreen(
                modifier = modifier,
                onPersonCardTap = onPersonCardTap,
                onGuestStarExpand = onGuestStarExpand,
                onCrewExpand = onCrewExpand,
                onBackPressed = onBackPressed,
                episode = uiState.season.episodes.first()
            )
        }
    }
}