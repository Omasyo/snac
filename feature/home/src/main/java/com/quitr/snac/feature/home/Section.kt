package com.quitr.snac.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.data.Show
import com.quitr.snac.core.data.ShowType
import com.quitr.snac.core.ui.ShowCard
import com.quitr.snac.core.ui.theme.SnacTheme

@Composable
internal fun Section(
    modifier: Modifier = Modifier,
    name: String,
    type: ShowType,
    shows: List<Show>,
    onExpand: () -> Unit
) {
    Column(modifier) {
        Row(
            Modifier
                .clickable(onClick = onExpand)
                .padding(horizontal = 16f.dp, vertical = 8f.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(name, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.width(4f.dp))
            TypeContainer(type)
            Spacer(Modifier.weight(1f))
            Icon(Icons.Default.ArrowForward, contentDescription = null)

        }
//        Spacer(Modifier.height(8f.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16f.dp),
            horizontalArrangement = Arrangement.spacedBy(8f.dp)
        ) {
            items(shows) { show ->
                ShowCard(
                    Modifier.size(120f.dp, 200f.dp),
                    title = show.title,
                    posterUrl = show.posterUrl,
                    rating = show.rating
                ) {

                }
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
            type.name.uppercase(),
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
        Section(name = "Section", type = ShowType.Movie, shows = shows, onExpand = {})
    }
}

private val shows = List(30) {
    Show(
        1,
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg"
    )
}
