package com.keetr.snac.core.ui.carousel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.common.R
import com.keetr.snac.core.model.Show
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.ui.card.ShowCard
import com.keetr.snac.core.ui.theme.SnacTheme

@Composable
fun ShowCarousel(
    modifier: Modifier = Modifier,
    name: String,
    type: ShowType,
    shows: List<Show>,
    onExpand: () -> Unit,
    onMovieCardClicked: (id: Int) -> Unit,
    onTvCardClicked: (id: Int) -> Unit,
) {
    Carousel(modifier, header = {
        Text(name, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.width(4f.dp))
        TypeContainer(type)
    }, onExpand = onExpand) {
        items(
            shows,
            key = { show -> show.id },
        ) { show ->
            ShowCard(Modifier.size(120f.dp, 200f.dp),
                title = show.title,
                posterUrl = show.posterUrl,
                rating = show.rating,
                onClick = {
                    when (show.showType) {
                        ShowType.Movie -> onMovieCardClicked(show.id)
                        ShowType.Tv -> onTvCardClicked(show.id)
                    }
                })
        }
    }
}

@Composable
fun ShowCarousel(
    modifier: Modifier = Modifier,
    name: String,
    shows: List<Show>,
    onMovieCardClicked: (id: Int) -> Unit,
    onTvCardClicked: (id: Int) -> Unit,
) {
    Carousel(
        modifier,
        header = {
            Box(
                Modifier
                    .padding(horizontal = 16f.dp, vertical = 8f.dp)
                    .fillMaxWidth()
                    .height(40f.dp),
            ) {
                Text(name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(
                    Alignment.CenterStart))
            }
        },
    ) {
        items(
            shows,
            key = { show -> show.id },
        ) { show ->
            ShowCard(Modifier.size(120f.dp, 200f.dp),
                title = show.title,
                posterUrl = show.posterUrl,
                rating = show.rating,
                onClick = {
                    when (show.showType) {
                        ShowType.Movie -> onMovieCardClicked(show.id)
                        ShowType.Tv -> onTvCardClicked(show.id)
                    }
                })
        }
    }
}


@Composable
fun TypeContainer(type: ShowType) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.extraSmall
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
        ShowCarousel(name = "Section",
//            type = ShowType.Movie,
            shows = shows,
//            onExpand = {},
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
