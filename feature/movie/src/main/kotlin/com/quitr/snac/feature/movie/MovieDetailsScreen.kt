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
import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.ui.InlineText
import com.quitr.snac.core.ui.ShowScaffold
import com.quitr.snac.core.ui.append
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.theme.SnacTheme
import kotlin.random.Random

@Composable
fun MovieRoute(
    modifier: Modifier = Modifier
) {
    MovieDetailsScreen(modifier = modifier, FakeMovie)
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, ExperimentalLayoutApi::class
)
@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movie: Movie,
) {
    val lazyColumnState = rememberLazyListState()

    val isCollapsed by remember {
        derivedStateOf {
            lazyColumnState.firstVisibleItemIndex > 0
        }
    }

    ShowScaffold(
        modifier = modifier,
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
                    style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic)
                )
            }
        }
        separator()
        item {
            Column(Modifier.padding(horizontal = 16f.dp)) {

                Text("Tags", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8f.dp))
                //TODO use verticalArrangement: https://issuetracker.google.com/issues/268365538
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8f.dp)
                ) {
                    for (keyword in movie.keywords) {
                        Box(
                            Modifier
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .clickable { }
                                .padding(horizontal = 16f.dp, vertical = 8f.dp)
                        ) {
                            Text(keyword.name, style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
//        seperator()
        item {

        }

        items(20) {
            Box(
                Modifier
                    .background(Color(0xFF000000 + Random.nextLong(0xFFFFFF)))
                    .height(64f.dp)
                    .fillMaxWidth()
            )
        }
    }
}

fun LazyListScope.separator(
    modifier: Modifier = Modifier.padding(16f.dp)
) {
    item {
        Divider(modifier)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieScreenPreview() {
    SnacTheme {
        MovieDetailsScreen(movie = FakeMovie)
    }
}