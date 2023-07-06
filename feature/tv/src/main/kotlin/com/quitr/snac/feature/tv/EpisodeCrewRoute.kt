package com.quitr.snac.feature.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.ui.show.ShowCreditsScreen


object EpisodeCrewRoute : NavigationRoute("tv/%s/season/%s/episode/%s/crew") {
    override val requiredArguments: List<String> = listOf(
        tvId,
        seasonNumber,
        episodeNumber
    )
}

@Composable
fun EpisodeCrewRoute(
    modifier: Modifier = Modifier,
    onPersonCardTapped: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SeasonScreenViewModel = hiltViewModel()
) {
    val state = viewModel.seasonScreenUiState.collectAsState().value
    if (state is SeasonScreenUiState.Success) {
        ShowCreditsScreen(
            modifier,
            title = stringResource(R.string.crew),
            onPersonCardTap = onPersonCardTapped,
            onBackPressed = onBackPressed,
            people = state.season.episodes.first().crew
        )
    }
}