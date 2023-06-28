package com.quitr.snac.feature.discover.discover


import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.section.Section
import com.quitr.snac.core.ui.section.SectionError
import com.quitr.snac.core.ui.section.SectionPlaceholder
import com.quitr.snac.core.ui.section.ShowCarouselUiState
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.feature.discover.R

@Composable
fun DiscoverRoute(
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun DiscoverScreen(
    modifier: Modifier = Modifier,
    showCarouselUiStates: Map<SectionType, ShowCarouselUiState>,
    onCarouselExpand: (SectionType) -> Unit,
    onMovieCardClicked: (id: Int) -> Unit,
    onTvCardClicked: (id: Int) -> Unit,
    onRetry: (SectionType) -> Unit,
) {
    Surface(modifier) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16f.dp),
            verticalArrangement = Arrangement.spacedBy(16f.dp)
        ) {
            items(SectionType.values(), { it.name }) { sectionType ->
                AnimatedContent(
                    targetState = showCarouselUiStates[sectionType] ?: ShowCarouselUiState.Error
                ) { uiState ->
                    when (uiState) {
                        ShowCarouselUiState.Error -> SectionError(
                            name = sectionType.title,
                            type = sectionType.showType,
                            onRetry = { onRetry(sectionType) })

                        ShowCarouselUiState.Loading -> SectionPlaceholder()
                        is ShowCarouselUiState.Success -> {
                            Section(
                                name = sectionType.title,
                                type = sectionType.showType,
                                shows = uiState.shows,
                                onExpand = { onCarouselExpand(sectionType) },
                                onMovieCardClicked = onMovieCardClicked,
                                onTvCardClicked = onTvCardClicked
                            )
                        }
                    }
                }
            }
        }
    }
}

internal val SectionType.title
    @Composable get() =
        when (this) {
            SectionType.MovieTrending, SectionType.TvTrending -> stringResource(R.string.trending)
            SectionType.MovieNowPlaying -> stringResource(R.string.now_playing)
            SectionType.MovieUpcoming -> stringResource(R.string.upcoming)
            SectionType.MoviePopular, SectionType.TvPopular -> stringResource(R.string.popular)
            SectionType.MovieTopRated, SectionType.TvTopRated -> stringResource(R.string.top_rated)
            SectionType.TvAiringToday -> stringResource(R.string.airing_today)
            SectionType.TvOnTheAir -> stringResource(R.string.on_the_air)
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    SnacTheme {
        DiscoverScreen(
            showCarouselUiStates = SectionType.values().associateWith {
                ShowCarouselUiState.Success(shows)
            },
            onCarouselExpand = {},
            onMovieCardClicked = {},
            onTvCardClicked = {},
            onRetry = {}
        )
    }
}

private val shows = List(30) {
    Show(
        it,
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        ShowType.Movie,
    )
} + Show(
    31,
    "Son of Sango",
    "9.2",
    "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
    ShowType.Movie
)