package com.quitr.snac.feature.discover.section

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.card.ShowCard
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.core.ui.utils.fadePlaceholder
import com.quitr.snac.core.ui.utils.plus
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ShowGridScreen(
    modifier: Modifier = Modifier,
    title: String,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    pagingItems: LazyPagingItems<Show>,
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
        when (pagingItems.loadState.refresh) {
            is LoadState.Error -> { /*TODO*/ }
            LoadState.Loading -> {
                GridScreenPlaceholder(
                    Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 12f.dp, vertical = 16f.dp)
                )
            }
            is LoadState.NotLoading -> {

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(96f.dp),
                    contentPadding = innerPadding + PaddingValues(16f.dp),
                    horizontalArrangement = Arrangement.spacedBy(8f.dp),
                    verticalArrangement = Arrangement.spacedBy(16f.dp),
                ) {
                    items(pagingItems.itemCount, pagingItems.itemKey { show -> show.id }) {
                        val show = pagingItems[it]!!
                        ShowCard(
                            Modifier.aspectRatio(3f / 5f),
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
                    if (pagingItems.loadState.append == LoadState.Loading) {
                        item {
                            Box(Modifier.aspectRatio(3f / 5f)) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentWidth(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GridScreenPlaceholder(modifier: Modifier = Modifier) {

    //TODO use arrangement: https://issuetracker.google.com/issues/268365538
    FlowRow(
        modifier, maxItemsInEachRow = 3
    ) {
        repeat(12) {
            Box(
                Modifier
                    .weight(1f)
                    .aspectRatio(3f / 5f)
                    .padding(horizontal = 4f.dp, vertical = 8f.dp)
                    .fadePlaceholder()
            )
        }
    }
}

@Preview(fontScale = 1.0f)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SectionScreenPreview() {
    SnacTheme {
        ShowGridScreen(Modifier, "Section", {}, {}, {},
            flow {
                emit(PagingData.from(shows))
            }.collectAsLazyPagingItems()
        )
    }
}

private val shows = List(20) {
    Show(
        0,
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        ShowType.Movie,
    )
}