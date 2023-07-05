package com.quitr.snac.core.ui.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.common.R
import com.quitr.snac.core.ui.theme.SnacTheme

@Composable
fun EpisodeCard(
    modifier: Modifier = Modifier,
    title: String,
    season: Int,
    episode: Int,
    description: String,
    posterUrl: String,
    onClick: () -> Unit
) {
    WideCard(
        modifier,
        title = {
            Row(
                Modifier
                    .height(56f.dp)
                    .padding(8f.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.width(8f.dp))
                Text(
                    "S${season.format()} E${episode.format()}",
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f)
                )
            }
        },
        posterUrl = posterUrl,
        posterDescription = stringResource(R.string.poster_description, title),
        onClick = onClick
    ) {
        Text(
            description,
            Modifier.padding(8f.dp),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

fun Int.format(): String = String.format("%02d", this)

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun EpisodeCardPreview() {

    SnacTheme {
        EpisodeCard(
            Modifier,
            title = "Stranger Things",
            season = 1,
            episode = 7,
            description = Description,
            posterUrl = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg"
        ) {

        }
    }
}

private const val Description =
    "Strange things are afoot in Hawkins, Indiana, where a young boy's" +
            "sudden disappearance unearths a young girl with otherworldly powers."