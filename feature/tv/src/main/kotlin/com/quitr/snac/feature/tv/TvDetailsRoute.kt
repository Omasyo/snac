package com.quitr.snac.feature.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.model.NavigationRoute

object TvDetailsRoute : NavigationRoute() {
    const val tvId = "tvId"

    override val requiredArguments: List<String>
        get() = listOf(tvId)

    override val root: String
        get() = "tv"

    fun route(id: Int) = route(
        mapOf(tvId to id)
    )
}

@Composable
fun TvDetailsRoute(
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
        uiState = uiState
    )
}