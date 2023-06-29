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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.carousel.ShowCarousel
import com.quitr.snac.core.ui.carousel.SectionError
import com.quitr.snac.core.ui.carousel.SectionPlaceholder
import com.quitr.snac.core.ui.carousel.ShowCarouselUiState
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.feature.discover.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun DiscoverScreen(
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
                            ShowCarousel(
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