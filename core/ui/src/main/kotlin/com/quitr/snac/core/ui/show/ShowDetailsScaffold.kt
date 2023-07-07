package com.quitr.snac.core.ui.show

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.Genre
import com.quitr.snac.core.model.Keyword
import com.quitr.snac.core.ui.ResizableHeaderScaffold
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.utils.InlineText
import com.quitr.snac.core.ui.utils.append
import com.quitr.snac.core.common.R as CommonR

@Composable
fun ShowDetailsScaffold(
    modifier: Modifier = Modifier,
    title: String,
    posterUrl: String,
    backdropUrl: String,
    releaseDate: String,
    runtime: Int,
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
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(16f/9f)
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
                            ),
                            modifier = Modifier.fillMaxSize()
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
                                listOf(releaseDate, pluralStringResource(CommonR.plurals.minutes_short, runtime, runtime)).filter(String::isNotBlank)//TODO extract to string
                                    .joinToString(" • "),
                                style = titleSmall
                            )

                            val genresString = genres.joinToString(" • ") { it.name }
                            Text(genresString, style = titleSmall)

                            InlineText(style = titleSmall) {
                                append(SnacIcons.Star)
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

fun LazyListScope.separator(
    modifier: Modifier = Modifier.padding(16f.dp)
) {
    item {
        Divider(modifier)
    }
}

@Composable
fun AboutDetails(
    modifier: Modifier = Modifier,
    info: String,
    details: List<String>,
    infoRatio: Float = 0.4f
) {
    Row(modifier) {
        Text(
            text = info,
            modifier = Modifier.fillMaxWidth(infoRatio),
            color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
        )
        Spacer(Modifier.width(4f.dp))
        Column(modifier = Modifier) {
            details.forEach { detail ->
                Text(text = detail)
            }
        }
    }
}

@Composable
fun AboutDetails(
    modifier: Modifier = Modifier,
    info: String,
    detail: String,
    infoRatio: Float = 0.4f
) = AboutDetails(modifier, info, listOf(detail), infoRatio)

@Composable
fun Tagline(tagline: String, modifier: Modifier) {
    Text(
        tagline,
        style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic),
        modifier = modifier
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(keywords: List<Keyword>, modifier: Modifier) {
    ShowSection(
        stringResource(R.string.tags),
        Modifier.padding(horizontal = 16f.dp)
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(
                8f.dp
            )
        ) {
            for (keyword in keywords) {
                Box(
                    Modifier
                        .clip(MaterialTheme.shapes.small)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant.copy(
                                0.4f
                            )
                        )
                        .clickable { }
                        .padding(horizontal = 16f.dp, vertical = 8f.dp)) {
                    Text(
                        keyword.name,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
fun Overview(overview: String, modifier: Modifier) {
    ShowSection(
        title = stringResource(R.string.overview),
        modifier,
    ) {
        Text(overview)
    }
}

@Composable
fun ShowSection(
    title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(16f.dp))
        content()
    }
}