package com.quitr.snac.feature.tv.episode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.feature.tv.season.SeasonScreenUiState
import com.quitr.snac.feature.tv.season.SeasonScreenViewModel

@Composable
internal fun EpisodeDetailsRoute(
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