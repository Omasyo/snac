package com.quitr.snac.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
fun PersonCard(
    name: String,
    role: String,
    photoUrl: String
) {
    OutlinedCard(
        Modifier
            .width(120f.dp)
            .height(200f.dp)
    ) {
        AsyncImage(
            contentDescription = stringResource(R.string.poster_description),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoUrl)
                .crossfade(true)
                .build(),
            placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
            error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(128f.dp)
        )
        Column(
            Modifier
                .height(72f.dp)
                .padding(8f.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                name,
                style = MaterialTheme.typography.titleSmall,

                overflow = TextOverflow.Ellipsis
            )
            Text(
                role,
                style = MaterialTheme.typography.labelSmall,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PersonCardPreview() {
    SnacTheme {
        PersonCard(
            name = "Christopher Mintz-Plasse",
            photoUrl = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
            role = "Chriss D'Amico / Red Mist"
        )
    }
}