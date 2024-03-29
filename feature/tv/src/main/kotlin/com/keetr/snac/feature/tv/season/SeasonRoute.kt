package com.keetr.snac.feature.tv.season

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.keetr.snac.feature.tv.details.TvDetailsViewModel
import com.keetr.snac.feature.tv.details.TvScreenUiState

@Composable
internal fun SeasonRoute(
    modifier: Modifier = Modifier,
    onSeasonCardTap: (tvId: Int, seasonNumber: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: TvDetailsViewModel = hiltViewModel()
) {
    val tvId = viewModel.id
    val state = viewModel.tvDetailsUiState.collectAsState().value

    if (state is TvScreenUiState.Success) {
        SeasonScreen(
            modifier = modifier,
            tvId = tvId,
            onSeasonCardTap = onSeasonCardTap,
            onBackPressed = onBackPressed,
            seasons = state.tv.seasons
        )
    }
}