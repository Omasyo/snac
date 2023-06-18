package com.quitr.snac.feature.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.common.R
import com.quitr.snac.core.data.Show
import com.quitr.snac.core.data.ShowType
import com.quitr.snac.core.ui.ShowCard
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.theme.SnacTheme
import kotlin.random.Random

@Composable
internal fun Section(
    modifier: Modifier = Modifier,
    name: String,
    type: ShowType,
    shows: List<Show>,
    onExpand: () -> Unit,
    onMovieCardClicked: (id: Int) -> Unit,
    onTvCardClicked: (id: Int) -> Unit,
) {
    Column(modifier) {
        Row(
            Modifier
                .clickable(onClick = onExpand)
                .padding(horizontal = 16f.dp, vertical = 12f.dp)
                .fillMaxWidth()
                .height(40f.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(name, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.width(4f.dp))
            TypeContainer(type)
            Spacer(Modifier.weight(1f))
            Icon(SnacIcons.ArrowForward, contentDescription = null)

        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16f.dp),
            horizontalArrangement = Arrangement.spacedBy(8f.dp)
        ) {
            items(
                shows,
                key = { show -> show.id },
            ) { show ->
                ShowCard(
                    Modifier.size(120f.dp, 200f.dp),
                    title = show.title,
                    posterUrl = show.posterUrl,
                    rating = show.rating,
                    onClick = {
                        when (show.showType) {
                            ShowType.Movie -> onMovieCardClicked(show.id)
                            ShowType.Tv -> onTvCardClicked(show.id)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun TypeContainer(type: ShowType) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Text(
            when (type) {
                ShowType.Movie -> stringResource(R.string.movie)
                ShowType.Tv -> stringResource(R.string.tv)
            }.uppercase(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 4f.dp, vertical = 2f.dp)
        )
    }
}

@Preview
@Composable
private fun SectionPreview() {
    SnacTheme {
        Section(
            name = "Section",
            type = ShowType.Movie,
            shows = shows,
            onExpand = {},
            onTvCardClicked = {},
            onMovieCardClicked = {})
    }
}

private val shows = List(30) {
    Show(
        it,
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        ShowType.Movie
    )
}