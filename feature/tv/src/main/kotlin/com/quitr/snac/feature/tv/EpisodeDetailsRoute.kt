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
import com.quitr.snac.core.model.EpisodeDetails
import com.quitr.snac.core.model.NavigationRoute


object EpisodeDetailsRoute : NavigationRoute() {
    const val tvId = "tvId"
    const val seasonNumber = "season_number"
    const val episodeNumber = "episode_number"

    override val root = "tv/episode"

    override val requiredArguments: List<String> = listOf(tvId, seasonNumber, episodeNumber)

    fun route(id: Int, seasonNo: Int, episodeNo: Int) = route(
        mapOf(
            tvId to id,
            seasonNumber to seasonNo,
            episodeNumber to episodeNo,
        )
    )
}

@Composable
fun EpisodeDetailsRoute(
    onPersonCardTap: (id: Int) -> Unit,
    onGuestStarExpand: () -> Unit,
    onCrewExpand: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SeasonScreenViewModel = hiltViewModel(),
) {
    when(val uiState = viewModel.seasonScreenUiState.collectAsState().value) {
        is SeasonScreenUiState.Error -> {
            Column(
                Modifier
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
                onPersonCardTap = onPersonCardTap,
                onGuestStarExpand = onGuestStarExpand,
                onCrewExpand = onCrewExpand,
                onBackPressed = onBackPressed,
                episode = uiState.season.episodes.first()
            )
        }
    }
}