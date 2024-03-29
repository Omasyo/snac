package com.keetr.snac.core.ui.card

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.keetr.snac.core.ui.theme.SnacTheme
import com.keetr.snac.core.common.R as CommonR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonCard(
    modifier: Modifier = Modifier,
    name: String,
    role: String,
    photoUrl: String,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier
    ) {
        AsyncImage(
            contentDescription = stringResource(CommonR.string.profile_image, name),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoUrl)
                .crossfade(true)
                .build(),
            placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
            error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(1f)
        )
        Column(
            Modifier
                .padding(8f.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                name,
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
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=120dp,height=200dp,dpi=440", fontScale = 0.85f
)
@Composable
private fun PersonCardPreview() {
    SnacTheme {
        PersonCard(
            name = "Christopher Mintz-Plasse",
            photoUrl = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
            role = "Chriss D'Amico / Red Mist",
        ) {

        }
    }
}