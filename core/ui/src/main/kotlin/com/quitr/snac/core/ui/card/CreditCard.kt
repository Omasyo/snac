package com.quitr.snac.core.ui.card

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.quitr.snac.core.common.R
import com.quitr.snac.core.ui.theme.SnacTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCard(
    modifier: Modifier = Modifier,
    title: String,
    role: String,
    rating: String,
    posterUrl: String,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick =  onClick,
        modifier
    ) {
        Box(Modifier.weight(1f)) {
            AsyncImage(
                contentDescription = stringResource(R.string.poster_description, title),
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
        Column(
            Modifier
                .padding(8f.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                role,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(device = "spec:width=120dp,height=200dp,dpi=440")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=120dp,height=200dp,dpi=440", fontScale = 0.85f
)
@Composable
private fun CreditCardPreview() {
    SnacTheme {
        CreditCard(
            title = "Son of Sango",
            posterUrl = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
            role = "Chriss D'Amico / Red Mist",
            rating = "9.8"
        ) {

        }
    }
}