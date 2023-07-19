package com.keetr.snac.feature.tv.episode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.keetr.snac.feature.tv.season.SeasonScreenViewModel

@Composable
internal fun EpisodeRoute(
    modifier: Modifier = Modifier,
    onEpisodeTap: (tvId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SeasonScreenViewModel = hiltViewModel()
) {
    val tvId = viewModel.tvId
    val uiState by viewModel.seasonScreenUiState.collectAsState()
    EpisodeScreen(
        modifier = modifier,
        tvId = tvId,
        onEpisodeTap = onEpisodeTap,
        onBackPressed = onBackPressed,
        uiState = uiState
    )
}