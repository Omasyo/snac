package com.quitr.snac.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WideCard(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    posterUrl: String,
    posterDescription: String?,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
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

                    contentDescription = posterDescription,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column {
                title()
                Divider()
                content()
            }
        }
    }

}