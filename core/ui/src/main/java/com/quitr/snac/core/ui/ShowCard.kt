package com.quitr.snac.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.quitr.snac.core.ui.theme.SnacTheme

@Composable
fun ShowCard(
    title: String,
    posterUrl: String,
    rating: String
) {
    OutlinedCard(
        Modifier
            .width(120f.dp)
            .height(200f.dp)
    ) {
        Box(Modifier.height(144f.dp)) {

            AsyncImage(
                contentDescription = stringResource(R.string.poster_description),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(posterUrl)
                    .crossfade(true)
                    .build(),
                placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .padding(8f.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(Color.Black.copy(0.75f)) //TODO: change color from black and text from white
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    rating,
                    Modifier.padding(horizontal = 4f.dp),
                    Color.White,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
        Text(
            title,
            Modifier
                .height(56f.dp)
                .padding(8f.dp),
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowCardPreview() {
    SnacTheme {
        ShowCard(
            title = "Son of Sango: The Return From The Evil Forest",
            posterUrl = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
            rating = "9.2"
        )
    }
}