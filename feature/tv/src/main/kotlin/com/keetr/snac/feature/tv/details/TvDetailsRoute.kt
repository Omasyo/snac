package com.keetr.snac.feature.tv.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun TvDetailsRoute(
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
        onRetry = viewModel::refresh,
        uiState = uiState
    )
}