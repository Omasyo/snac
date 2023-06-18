package com.quitr.snac.feature.discover

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.data.SectionType
import com.quitr.snac.core.data.SectionType.*
import com.quitr.snac.core.data.Show
import com.quitr.snac.core.data.ShowType
import com.quitr.snac.core.ui.ShowCard
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.theme.SnacTheme

@Composable
fun SectionRoute(
    modifier: Modifier = Modifier,
    sectionType: SectionType,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    SectionScreen(
        modifier,
        title = sectionType.title,
        shows = shows,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionScreen(
    modifier: Modifier = Modifier,
    title: String,
    shows: List<Show>,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(SnacIcons.ArrowBack, null)
                    }
                },
                title = {
                    Text(title)
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(96f.dp),
            contentPadding = innerPadding + PaddingValues(16f.dp),
            horizontalArrangement = Arrangement.spacedBy(8f.dp),
            verticalArrangement = Arrangement.spacedBy(16f.dp),
        ) {
            items(shows) { show ->
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
                    }
                )
            }
        }
    }
}


@Preview(fontScale = 1.0f)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SectionScreenPreview() {
    SnacTheme {
        SectionScreen(
            Modifier,
            "Section",
            shows,
            {},
            {},
            {}
        )
    }
}

private operator fun PaddingValues.plus(other: PaddingValues) =
    PaddingValues(

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