package com.quitr.snac.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.data.SectionType
import com.quitr.snac.core.data.Show
import com.quitr.snac.core.data.ShowType
import com.quitr.snac.core.ui.theme.SnacTheme

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onSectionClicked: (SectionType) -> Unit,
    onMovieCardClicked: (id: Int) -> Unit,
    onTvCardClicked: (id: Int) -> Unit,
) {
    HomeScreen(
        modifier,
        onSectionClicked,
        onMovieCardClicked,
        onTvCardClicked
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    onSectionClicked: (SectionType) -> Unit,
    onMovieCardClicked: (id: Int) -> Unit,
    onTvCardClicked: (id: Int) -> Unit,
) {
    Surface(modifier) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16f.dp),
            verticalArrangement = Arrangement.spacedBy(8f.dp)
        ) {
            items(10) {
                Section(
                    name = "Section", type = ShowType.Movie, shows = shows,
                    onExpand = { onSectionClicked(SectionType.Movie.NowPlaying) },
                    onMovieCardClicked = onMovieCardClicked,
                    onTvCardClicked = onTvCardClicked
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    SnacTheme {
        HomeScreen(
            onSectionClicked = {},
            onMovieCardClicked = {},
            onTvCardClicked = {}
        )
    }
}

private val shows = List(15) {
    Show(
        0,
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        ShowType.Movie,
    )
}