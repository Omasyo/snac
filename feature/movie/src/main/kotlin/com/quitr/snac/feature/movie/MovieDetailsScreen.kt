package com.quitr.snac.feature.movie

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.ui.AboutDetails
import com.quitr.snac.core.ui.PersonCarousel
import com.quitr.snac.core.ui.ShowDetailsPlaceholder
import com.quitr.snac.core.ui.ShowScaffold
import com.quitr.snac.core.ui.section.Section
import com.quitr.snac.core.ui.separator
import com.quitr.snac.core.ui.theme.SnacTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onPersonCardTap: (id: Int) -> Unit,
    onRecommendationsExpand: () -> Unit,
    onBackPressed: () -> Unit,
    uiState: MovieDetailsUiState,
) {
    when (uiState) {
        MovieDetailsUiState.Error -> TODO()
        MovieDetailsUiState.Loading -> ShowDetailsPlaceholder(onBackPressed = onBackPressed)
        is MovieDetailsUiState.Success -> {
            val movie = uiState.movie
            ShowScaffold(modifier = modifier,
                title = movie.title,
                posterUrl = movie.posterUrl,
                backdropUrl = movie.backDropUrl,
                releaseDate = movie.releaseDate,
                runtime = movie.runtime.toString(),
                genres = movie.genres,
                voteAverage = movie.voteAverage.toString(),
                voteCount = movie.voteCount,
                onBackPressed = onBackPressed) {
                if (movie.tagline.isNotBlank()) {
                    separator()
                    item {
                        Column(Modifier.padding(horizontal = 16f.dp)) {
                            Text(
                                movie.tagline,
                                style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic)
                            )
                        }
                    }
                }
                separator()
                item {
                    MovieSection("Tags", Modifier.padding(horizontal = 16f.dp)) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8f.dp)
                        ) {
                            for (keyword in movie.keywords) {
                                Box(Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .clickable { }
                                    .padding(horizontal = 16f.dp, vertical = 8f.dp)) {
                                    Text(keyword.name, style = MaterialTheme.typography.labelLarge)
                                }
                            }
                        }
                    }
                }
                separator()
                item {
                    MovieSection(title = "Overview", Modifier.padding(horizontal = 16f.dp)) {
                        Text(movie.overview)
                    }
                }
                separator()
                item {
                    PersonCarousel(category = "Cast",
                        people = movie.cast,
                        onExpand = { /*TODO*/ },
                        onPersonClicked = onPersonCardTap
                    )
                }
                item { Spacer(Modifier.height(16f.dp)) }
                if(movie.crew.isNotEmpty()) {
                    item {
                        PersonCarousel(category = "Crew",
                            people = movie.crew,
                            onExpand = { /*TODO*/ },
                            onPersonClicked = onPersonCardTap
                        )
                    }
                }
                separator()
                item {
                    MovieSection(title = "About", Modifier.padding(horizontal = 16f.dp)) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8f.dp)
                        ) {
                            AboutDetails(info = "Original Title", detail = movie.originalTitle)
                            AboutDetails(info = "Release Date", detail = movie.releaseDate)
                            AboutDetails(info = "Runtime", detail = movie.runtime.toString())
                            AboutDetails(info = "Status", detail = movie.status)
                            AboutDetails(info = "Budget", detail = movie.budget.toString())
                            AboutDetails(info = "Revenue", detail = movie.revenue.toString())
                            AboutDetails(
                                info = "Original Language", detail = movie.originalLanguage
                            )
//                    AboutDetails(info = "Country of Origin", detail = movie)
                            AboutDetails(
                                info = "Production Companies", details = movie.productionCompanies
                            )
                            AboutDetails(
                                info = "Production Countries", details = movie.productionCompanies
                            )

                        }
                    }
                }
                separator()
                if(movie.recommendations.isNotEmpty()) {
                    item {
                        Section(
                            name = "Recommendations",
                            shows = movie.recommendations,
                            onExpand = onRecommendationsExpand,
                            onMovieCardClicked = onMovieCardTap,
                            onTvCardClicked = onTvCardTap,
                        )
                    }
                }
                item {
                    Section(
                        name = "Similar",
                        shows = movie.similar,
                        onExpand = { /*TODO*/ },
                        onMovieCardClicked = onMovieCardTap,
                        onTvCardClicked = onTvCardTap,
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieSection(
    title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(16f.dp))
        content()
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, device = "spec:width=1080px,height=6000px,dpi=440"
)
@Composable
private fun MovieScreenPreview() {
    SnacTheme {
        MovieDetailsScreen(
            uiState = MovieDetailsUiState.Loading,
            onMovieCardTap = {},
            onTvCardTap = {},
            onPersonCardTap = {},
            onRecommendationsExpand = {},
            onBackPressed = {}
        )
    }
}