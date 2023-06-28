package com.quitr.snac.core.ui

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        title = {
            Row(
                Modifier
                    .height(56f.dp)
                    .padding(8f.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium
//                            Modifier.align(Alignment.Center)
                )
                Spacer(Modifier.width(8f.dp))
                Text(
                    "S${season.format()} E${episode.format()}",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        },
        posterUrl = posterUrl,
        posterDescription = stringResource(com.quitr.snac.core.common.R.string.poster_description, title),
        onClick = onClick
    ) {
        Text(
            description,
            Modifier.padding(8f.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

fun Int.format() : String = String.format("%02d", this)

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

private const val Description = "Strange things are afoot in Hawkins, Indiana, where a young boy's" +
        "sudden disappearance unearths a young girl with otherworldly powers."