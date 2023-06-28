package com.quitr.snac.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.quitr.snac.core.common.R as CommonR
import com.quitr.snac.core.model.Genre
import com.quitr.snac.core.ui.theme.SnacIcons


@Composable
fun ShowScaffold(
    modifier: Modifier = Modifier,
    title: String,
    posterUrl: String,
    backdropUrl: String,
    releaseDate: String,
    runtime: String,
    genres: List<Genre>,
    voteAverage: String,
    voteCount: Int,
    onBackPressed: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    ResizableHeaderScaffold(
        modifier = modifier,
        title = title,
        onBackPressed = onBackPressed,
        content = content,
        expandedContent = {
            Box(
                Modifier
                    .height(376.dp)
                    .fillMaxWidth()
            ) {
                val backgroundColor = MaterialTheme.colorScheme.surface
                AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(backdropUrl)
                    .crossfade(true).build(),
                    placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                    error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                    contentDescription = stringResource(CommonR.string.backdrop_description, title),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .height(220f.dp)
                        .fillMaxWidth()
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, backgroundColor),
                                startY = size.height / 5,
                                endY = size.height
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient)
                            }
                        })
                Row(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(16f.dp)
                ) {
                    ElevatedCard(Modifier.size(120f.dp, 176f.dp)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current).data(posterUrl)
                                .crossfade(true).build(),
                            placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                            error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                            contentScale = ContentScale.Crop,
                            contentDescription = stringResource(
                                CommonR.string.poster_description, title
                            )
                        )
                    }
                    Spacer(Modifier.width(24f.dp))
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        verticalArrangement = Arrangement.spacedBy(8f.dp)
                    ) {
                        with(MaterialTheme.typography) {
                            Text(title, style = titleLarge)

                            Text(
                                "$releaseDate • $runtime mins", style = titleSmall
                            )

                            val genresString = genres.joinToString(" • ") { it.name }
                            Text(genresString, style = titleSmall)

                            InlineText(style = titleSmall) {
                                append(SnacIcons.HomeFilled)
                                append(" $voteAverage • ")
                                append(SnacIcons.People)
                                append(" $voteCount")
                            }
                        }
                    }
                }
            }
        },
    )
}