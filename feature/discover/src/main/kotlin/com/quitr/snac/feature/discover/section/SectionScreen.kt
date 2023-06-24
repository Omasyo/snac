package com.quitr.snac.feature.discover.section

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.quitr.snac.core.data.getMovieRepository
import com.quitr.snac.core.data.getTvRepository
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.ShowCard
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.feature.discover.discover.title

@Composable
fun SectionRoute(
    modifier: Modifier = Modifier,
    sectionType: SectionType,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SectionScreenViewModel = viewModel(
        factory = SectionScreenViewModel.Factory(
            sectionType, getMovieRepository(), getTvRepository()
        )
    )
) {
    val uiState by viewModel.sectionScreenUiState.collectAsState()
    SectionScreen(
        modifier,
        title = sectionType.title,
        uiState = uiState,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed,
        onLoadMore = viewModel::addNextPage
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun SectionScreen(
    modifier: Modifier = Modifier,
    title: String,
    uiState: SectionScreenUiState,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    onLoadMore: () -> Unit,
) {
    Scaffold(modifier, topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(SnacIcons.ArrowBack, null)
            }
        }, title = {
            Text(title)
        })
    }) { innerPadding ->
        when (uiState) {
            SectionScreenUiState.Error -> TODO()
            SectionScreenUiState.Loading -> {
                SectionScreenPlaceholder(
                    Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 12f.dp, vertical = 16f.dp)
                )
            }

            is SectionScreenUiState.Success -> {
                val lazyGridState = rememberLazyGridState()

                val shouldLoadMore by remember(uiState, lazyGridState) {
                    derivedStateOf {
                        (lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                            ?: 0) >= uiState.shows.lastIndex - 10
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(96f.dp),
                    state = lazyGridState,
                    contentPadding = innerPadding + PaddingValues(16f.dp),
                    horizontalArrangement = Arrangement.spacedBy(8f.dp),
                    verticalArrangement = Arrangement.spacedBy(16f.dp),
                ) {
                    items(uiState.shows, { show -> show.id }) { show ->
                        ShowCard(Modifier.aspectRatio(3f / 5f),
                            title = show.title,
                            posterUrl = show.posterUrl,
                            rating = show.rating,
                            onClick = {
                                when (show.showType) {
                                    ShowType.Movie -> onMovieCardTap(show.id)
                                    ShowType.Tv -> onTvCardTap(show.id)
                                }
                            })
                    }
                    
                    if (uiState.isLoading) {
                        item {
                            Box(Modifier.aspectRatio(3f / 5f)) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
                if (shouldLoadMore) {
                    onLoadMore()
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SectionScreenPlaceholder(modifier: Modifier = Modifier) {
    FlowRow(
        modifier,
        maxItemsInEachRow = 3
    ) {
        repeat(12) {
            Box(
                Modifier
                    .weight(1f)
                    .aspectRatio(3f / 5f)
                    .padding(horizontal = 4f.dp, vertical = 8f.dp)
                    .placeholder(
                        true,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        highlight = PlaceholderHighlight.fade(
                            MaterialTheme.colorScheme.surfaceVariant,
                        )
                    )
            )
        }
    }
}

@Preview(fontScale = 1.0f)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SectionScreenPreview() {
    SnacTheme {
        SectionScreen(Modifier, "Section", SectionScreenUiState.Loading, {}, {}, {}, {})
    }
}

private operator fun PaddingValues.plus(other: PaddingValues) = PaddingValues(

    this.calculateStartPadding(LayoutDirection.Ltr) + other.calculateStartPadding(
        LayoutDirection.Ltr
    ),
    this.calculateTopPadding() + other.calculateTopPadding(),
    this.calculateEndPadding(LayoutDirection.Ltr) + other.calculateEndPadding(LayoutDirection.Ltr),
    this.calculateBottomPadding() + other.calculateBottomPadding()
)

private val shows = List(20) {
    Show(
        0,
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        ShowType.Movie,
    )
}