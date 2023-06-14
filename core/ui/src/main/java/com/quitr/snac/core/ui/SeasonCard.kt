package com.quitr.snac.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.quitr.snac.core.ui.theme.SnacTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonCard(
    modifier: Modifier = Modifier,
    season: Int,
    releaseYear: Int,
    episodeCount: Int,
    description: String,
    posterUrl: String,
    onClick: () -> Unit
) {
        OutlinedCard(
            onClick = onClick,
            modifier
        ) {
            Row {
                Box(
                    Modifier
                        .aspectRatio(2f / 3f)
                        .fillMaxHeight()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(posterUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                        error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),

                        contentDescription = stringResource(R.string.poster_description),
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Column {
                    Row(
                        Modifier
                            .height(56f.dp)
                            .padding(8f.dp),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            stringResource(R.string.season, season),
                            style = MaterialTheme.typography.titleMedium
//                            Modifier.align(Alignment.Center)
                        )
                        Spacer(Modifier.width(8f.dp))
                        Text(
                            "$releaseYear | $episodeCount ${stringResource(R.string.episodes)}",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Divider()
                    Text(
                        description,
                        Modifier.padding(8f.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun SeasonCardPreview() {

    SnacTheme {
        SeasonCard(
            Modifier,

            season = 1,
            releaseYear = 2012,
            episodeCount = 7,
            description = Description,
            posterUrl = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg"
        ) {

        }
    }
}

const val Description = "Strange things are afoot in Hawkins, Indiana, where a young boy's" +
        "sudden disappearance unearths a young girl with otherworldly powers."