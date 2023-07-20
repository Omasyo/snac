package com.keetr.snac.feature.tv.details

import android.content.res.Configuration
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.common.R
import com.keetr.snac.core.model.Season
import com.keetr.snac.core.ui.ErrorScreen
import com.keetr.snac.core.ui.card.EpisodeCard
import com.keetr.snac.core.ui.card.SeasonCard
import com.keetr.snac.core.ui.carousel.PersonCarousel
import com.keetr.snac.core.ui.carousel.ShowCarousel
import com.keetr.snac.core.ui.show.AboutDetails
import com.keetr.snac.core.ui.show.Overview
import com.keetr.snac.core.ui.show.ShowDetailsPlaceholder
import com.keetr.snac.core.ui.show.ShowDetailsScaffold
import com.keetr.snac.core.ui.show.ShowSection
import com.keetr.snac.core.ui.show.Tagline
import com.keetr.snac.core.ui.show.Tags
import com.keetr.snac.core.ui.show.separator
import com.keetr.snac.core.ui.theme.SnacIcons
import com.keetr.snac.core.ui.theme.SnacTheme
import com.keetr.snac.feature.tv.fake.FakeTv

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TvDetailsScreen(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onPersonCardTap: (id: Int) -> Unit,
    onEpisodeCardTap: (showId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onSeasonCardTap: (showId: Int, seasonNumber: Int) -> Unit,
    onSeasonsExpand: () -> Unit,
    onCastExpand: () -> Unit,
    onCrewExpand: () -> Unit,
    onRetry: () -> Unit,
    onBackPressed: () -> Unit,
    uiState: TvScreenUiState,
) {
    when (uiState) {
        is TvScreenUiState.Error -> {
            ErrorScreen(onRetry = onRetry)
        }
        TvScreenUiState.Loading -> ShowDetailsPlaceholder(onBackPressed = onBackPressed)
        is TvScreenUiState.Success -> {

            with(uiState.tv) {
                ShowDetailsScaffold(
                    modifier = modifier,
                    title = name,
                    posterUrl = posterUrl,
                    backdropUrl = backdropUrl,
                    releaseDate = firstAirDate,
                    runtime = runtime,
                    genres = genres,
                    voteAverage = voteAverage,
                    voteCount = voteCount,
                    onBackPressed = onBackPressed
                ) {
                    if (tagline.isNotBlank()) {
                        separator()
                        item {
                            Tagline(
                                tagline, modifier = Modifier.padding(horizontal = 16f.dp)
                            )
                        }
                    }
                    if (keywords.isNotEmpty()) {
                        separator()
                        item {
                            Tags(keywords, Modifier.padding(horizontal = 16f.dp))
                        }
                    }
                    separator()
                    if (overview.isNotBlank()) {
                        item {
                            Overview(overview, Modifier.padding(horizontal = 16f.dp))
                        }
                        separator()
                    }
                    nextEpisodeToAir?.let { episode ->
                        item {
                            ShowSection(
                                stringResource(R.string.upcoming_episode),
                                Modifier.padding(horizontal = 16f.dp)
                            ) {
                                EpisodeCard(Modifier.height(176f.dp),
                                    title = episode.name,
                                    season = episode.seasonNumber,
                                    episode = episode.episodeNumber,
                                    description = episode.overview,
                                    posterUrl = episode.stillUrl,
                                    onClick = {
                                        onEpisodeCardTap(
                                            id,
                                            episode.seasonNumber,
                                            episode.episodeNumber
                                        )
                                    })
                            }
                        }
                        separator(
                            Modifier
                                .padding(horizontal = 16f.dp)
                                .padding(top = 16f.dp, bottom = 4f.dp)
                        )
                    }
                    item {
                        SeasonCarousel(
                            seasons = seasons,
                            tvId = id,
                            onExpand = onSeasonsExpand,
                            onSeasonCardTap = onSeasonCardTap
                        )
                    }
                    separator()
                    item {
                        PersonCarousel(
                            category = stringResource(R.string.cast),
                            people = cast,
                            onExpand = onCastExpand,
                            onPersonClicked = onPersonCardTap
                        )
                    }
                    item { Spacer(Modifier.height(16f.dp)) }
                    if (crew.isNotEmpty()) {
                        item {
                            PersonCarousel(
                                category = stringResource(R.string.crew),
                                people = crew,
                                onExpand = onCrewExpand,
                                onPersonClicked = onPersonCardTap
                            )
                        }
                    }
                    separator()
                    item {
                        ShowSection(
                            title = stringResource(R.string.about),
                            Modifier.padding(horizontal = 16f.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8f.dp)
                            ) {
                                AboutDetails(
                                    info = stringResource(R.string.created_by), details = creators
                                )
                                AboutDetails(
                                    info = stringResource(R.string.original_title),
                                    detail = originalName
                                )
                                AboutDetails(
                                    info = stringResource(R.string.first_air_date),
                                    detail = firstAirDate
                                )
                                AboutDetails(
                                    info = stringResource(R.string.last_air_date),
                                    detail = lastAirDate
                                )
                                AboutDetails(
                                    info = stringResource(R.string.aired_episodes),
                                    detail = episodeCount.toString()
                                )
                                if(runtime != 0) {
                                    AboutDetails(
                                        info = stringResource(R.string.runtime),
                                        detail = pluralStringResource(
                                            R.plurals.minutes_short, runtime, runtime
                                        )
                                    )
                                }
                                AboutDetails(
                                    info = stringResource(R.string.show_type), detail = type
                                )
                                AboutDetails(
                                    info = stringResource(R.string.status), detail = status
                                )
//                                AboutDetails(
//                                    info = stringResource(R.string.original_language),
//                                    detail = originalLanguage
//                                )
                                AboutDetails(info = "Country of Origin", details = originCountry)
                                AboutDetails(
                                    info = pluralStringResource(
                                        R.plurals.production_companies, productionCompanies.size
                                    ), details = productionCompanies
                                )
                                AboutDetails(
                                    info = pluralStringResource(
                                        R.plurals.production_countries, productionCountries.size
                                    ), details = productionCompanies
                                )

                            }
                        }
                    }
                    separator()
                    if (recommendations.isNotEmpty()) {
                        item {
                            ShowCarousel(
                                name = stringResource(R.string.recommendations),
                                shows = recommendations,
                                onMovieCardClicked = onMovieCardTap,
                                onTvCardClicked = onTvCardTap,
                            )
                        }
                    }
                    if (similar.isNotEmpty()) {
                        item {
                            ShowCarousel(
                                name = stringResource(R.string.similar),
                                shows = similar,
                                onMovieCardClicked = onMovieCardTap,
                                onTvCardClicked = onTvCardTap,
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SeasonCarousel(
    modifier: Modifier = Modifier,
    seasons: List<Season>,
    tvId: Int,
    onExpand: () -> Unit,
    onSeasonCardTap: (id: Int, seasonNumber: Int) -> Unit,
    pagerState: PagerState = rememberPagerState(initialPage = seasons.lastIndex)
) {
    Column(modifier) {
        Row(
            Modifier
                .clickable(onClick = onExpand)
                .padding(horizontal = 16f.dp, vertical = 4f.dp)
                .fillMaxWidth()
                .height(40f.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                pluralStringResource(R.plurals.seasons, seasons.size),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.weight(1f))
            Icon(SnacIcons.ArrowForward, contentDescription = null)
        }
        HorizontalPager(
            state = pagerState,
            pageCount = seasons.size,
            contentPadding = PaddingValues(horizontal = 16f.dp),
            pageSpacing = 32f.dp,
            modifier = Modifier.height(176f.dp),
            key = { seasons[it].id },
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                lowVelocityAnimationSpec = tween(
                    easing = LinearOutSlowInEasing,
                    durationMillis = 700
                )
            )
        ) { index ->
            val season = seasons[index]
            SeasonCard(
                title = season.name,
                season = season.seasonNumber,
                airDate = season.airDate,
                episodeCount = season.episodeCount,
                description = season.overview,
                posterUrl = season.posterUrl,
                onClick = { onSeasonCardTap(tvId, season.seasonNumber) }
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, device = "spec:width=1080px,height=6000px,dpi=440"
)
@Composable
private fun TvScreenPreview() {
    SnacTheme {
        TvDetailsScreen(uiState = TvScreenUiState.Success(FakeTv),
            onMovieCardTap = {},
            onTvCardTap = {},
            onPersonCardTap = {},
            onCastExpand = {},
            onCrewExpand = {},
            onBackPressed = {},
            onSeasonsExpand = {},
            onEpisodeCardTap = { _, _, _ -> },
            onSeasonCardTap = { _, _ -> },
            onRetry = {}
        )
    }
}