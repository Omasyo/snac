package com.keetr.snac.feature.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun MovieDetailsRoute(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onPersonCardTap: (id: Int) -> Unit,
    onCastExpand: () -> Unit,
    onCrewExpand: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.movieDetailsUiState.collectAsState()
    MovieDetailsScreen(
        modifier = modifier,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed,
        onCastExpand = onCastExpand,
        onCrewExpand = onCrewExpand,
        onRetry = viewModel::refresh,
        uiState = uiState
    )
}
