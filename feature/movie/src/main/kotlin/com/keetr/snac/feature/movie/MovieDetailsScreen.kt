package com.keetr.snac.feature.movie

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.common.R
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
import com.keetr.snac.core.ui.theme.SnacTheme

@Composable
internal fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onPersonCardTap: (id: Int) -> Unit,
    onCastExpand: () -> Unit,
    onCrewExpand: () -> Unit,
    onBackPressed: () -> Unit,
    uiState: MovieDetailsUiState,
) {
    when (uiState) {
        is MovieDetailsUiState.Error -> {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16f.dp)
                    .padding(top = 36f.dp)
            ) {
                Text(
                    uiState.error.message ?: "", style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        MovieDetailsUiState.Loading -> ShowDetailsPlaceholder(onBackPressed = onBackPressed)
        is MovieDetailsUiState.Success -> {

            with(uiState.movie) {
                ShowDetailsScaffold(
                    modifier = modifier,
                    title = title,
                    posterUrl = posterUrl,
                    backdropUrl = backDropUrl,
                    releaseDate = releaseDate,
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
                    if (cast.isNotEmpty()) {
                        item {
                            PersonCarousel(
                                category = stringResource(R.string.cast),
                                people = cast,
                                onExpand = onCastExpand,
                                onPersonClicked = onPersonCardTap   
                            )
                        }
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
                                    info = stringResource(R.string.original_title),
                                    detail = originalTitle
                                )
                                AboutDetails(
                                    info = stringResource(R.string.release_date),
                                    detail = releaseDate
                                )
                                if (runtime != 0) {
                                    AboutDetails(
                                        info = stringResource(R.string.runtime),
                                        detail = pluralStringResource(
                                            R.plurals.minutes_short, runtime, runtime
                                        )
                                    )
                                }
                                AboutDetails(
                                    info = stringResource(R.string.status), detail = status
                                )
                                if (budget.isNotEmpty()) {
                                    AboutDetails(
                                        info = stringResource(R.string.budget), detail = budget
                                    )
                                }
                                if (revenue.isNotEmpty()) {
                                    AboutDetails(
                                        info = stringResource(R.string.revenue), detail = revenue
                                    )
                                }
//                                AboutDetails(
//                                    info = stringResource(R.string.original_language),
//                                    detail = originalLanguage
//                                )
//                    AboutDetails(info = "Country of Origin", detail = movie)
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

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, device = "spec:width=1080px,height=6000px,dpi=440"
)
@Composable
private fun MovieScreenPreview() {
    SnacTheme {
        MovieDetailsScreen(uiState = MovieDetailsUiState.Loading,
            onMovieCardTap = {},
            onTvCardTap = {},
            onPersonCardTap = {},
            onCastExpand = {},
            onCrewExpand = {},
            onBackPressed = {})
    }
}