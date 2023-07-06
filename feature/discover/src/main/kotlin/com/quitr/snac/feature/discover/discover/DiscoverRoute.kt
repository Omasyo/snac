package com.quitr.snac.feature.discover.discover

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.ui.carousel.ShowCarouselUiState

@Composable
internal fun DiscoverRoute(
    modifier: Modifier = Modifier,
    onCarouselExpand: (SectionType) -> Unit,
    onMovieCardClicked: (id: Int) -> Unit,
    onTvCardClicked: (id: Int) -> Unit,
    viewModel: DiscoverScreenViewModel = hiltViewModel()
) {
    DiscoverScreen(
        modifier,
        mapSectionTypeToUiState(viewModel),
        onCarouselExpand,
        onMovieCardClicked,
        onTvCardClicked,
        viewModel::retrySectionFetch
    )
}

@Composable
private fun mapSectionTypeToUiState(viewModel: DiscoverScreenViewModel): Map<SectionType, ShowCarouselUiState> {
    val map = mutableMapOf<SectionType, ShowCarouselUiState>()

    for (type in SectionType.values()) {
        val state by when (type) {
            SectionType.MoviePopular -> viewModel.moviePopularState.collectAsState()
            SectionType.MovieTrending -> viewModel.movieTrendingState.collectAsState()
            SectionType.MovieNowPlaying -> viewModel.movieNowPlayingState.collectAsState()
            SectionType.MovieUpcoming -> viewModel.movieUpcomingState.collectAsState()
            SectionType.MovieTopRated -> viewModel.movieTopRatedState.collectAsState()
            SectionType.TvTrending -> viewModel.tvTrendingState.collectAsState()
            SectionType.TvAiringToday -> viewModel.tvAiringTodayState.collectAsState()
            SectionType.TvOnTheAir -> viewModel.tvOnTheAirState.collectAsState()
            SectionType.TvPopular -> viewModel.tvPopularState.collectAsState()
            SectionType.TvTopRated -> viewModel.tvTopRatedState.collectAsState()
        }
        map[type] = state
    }
    return map
}