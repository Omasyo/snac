package com.quitr.snac.feature.tv.episode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.common.R
import com.quitr.snac.core.ui.show.ShowCreditsScreen
import com.quitr.snac.feature.tv.season.SeasonScreenUiState
import com.quitr.snac.feature.tv.season.SeasonScreenViewModel


@Composable
internal fun EpisodeGuestStarsRoute(
    modifier: Modifier = Modifier,
    onPersonCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SeasonScreenViewModel = hiltViewModel()
) {
    val state = viewModel.seasonScreenUiState.collectAsState().value
    if (state is SeasonScreenUiState.Success) {
        ShowCreditsScreen(
            modifier,
            title = stringResource(R.string.guest_stars),
            onPersonCardTap = onPersonCardTap,
            onBackPressed = onBackPressed,
            people = state.season.episodes.first().guestStars
        )
    }
}