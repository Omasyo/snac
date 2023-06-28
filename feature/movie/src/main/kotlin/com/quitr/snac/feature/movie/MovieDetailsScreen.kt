package com.quitr.snac.feature.movie

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.EpisodeCard
import com.quitr.snac.core.ui.InlineText
import com.quitr.snac.core.ui.PersonScroll
import com.quitr.snac.core.ui.ShowScaffold
import com.quitr.snac.core.ui.append
import com.quitr.snac.core.ui.section.Section
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.theme.SnacTheme
import java.io.File.separator
import kotlin.random.Random

@Composable
fun MovieRoute(
    modifier: Modifier = Modifier, viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.movieDetailsUiState.collectAsState()
    MovieDetailsScreen(modifier = modifier, uiState = uiState)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: MovieDetailsUiState,
) {
    when (uiState) {
        MovieDetailsUiState.Error -> TODO()
        MovieDetailsUiState.Loading -> { Text("Loading")}
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
                onBackPressed = { /*TODO*/ }) {
                separator()
                item {
                    Column(Modifier.padding(horizontal = 16f.dp)) {
                        Text(
                            movie.tagline,
//                    style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic)
                        )
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
                    PersonScroll(category = "Cast",
                        people = movie.cast,
                        onExpand = { /*TODO*/ },
                        onPersonClicked = {})
                }
                item { Spacer(Modifier.height(16f.dp)) }
                item {
                    PersonScroll(category = "Crew",
                        people = movie.crew,
                        onExpand = { /*TODO*/ },
                        onPersonClicked = {})
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
                item {
                    Section(
                        name = "Recommendations",
                        shows = movie.recommendations,
                        onExpand = { /*TODO*/ },
                        onMovieCardClicked = {},
                        onTvCardClicked = {},
                    )
                }
                item {
                    Section(
                        name = "Similar",
                        shows = movie.similar,
                        onExpand = { /*TODO*/ },
                        onMovieCardClicked = {},
                        onTvCardClicked = {},
                    )
                }
            }
        }
    }
}

@Composable
fun AboutDetails(
    modifier: Modifier = Modifier,
    info: String,
    details: List<String>,
) {
    Row(modifier) {
        Text(
            text = info,
            modifier = Modifier.fillMaxWidth(0.4f),
            color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
        )
        Spacer(Modifier.width(4f.dp))
        Column(modifier = Modifier) {
            details.forEach { detail ->
                Text(text = detail)
            }
        }
    }
}

@Composable
fun AboutDetails(
    modifier: Modifier = Modifier,
    info: String,
    detail: String,
) = AboutDetails(modifier, info, listOf(detail))

@Composable
fun MovieSection(
    title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(16f.dp))
        content()
    }
}

fun LazyListScope.separator(
    modifier: Modifier = Modifier.padding(16f.dp)
) {
    item {
        Divider(modifier)
    }
}

//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES, device = "spec:width=1080px,height=6000px,dpi=440"
//)
//@Composable
//fun MovieScreenPreview() {
//    SnacTheme {
//        MovieDetailsScreen(movie = FakeMovie)
//    }
//}