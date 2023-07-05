package com.quitr.snac.feature.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.EpisodeDetails
import com.quitr.snac.core.ui.ResizableHeaderScaffold
import com.quitr.snac.core.ui.carousel.PersonCarousel
import com.quitr.snac.core.ui.show.Overview
import com.quitr.snac.core.ui.show.separator
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.utils.InlineText
import com.quitr.snac.core.ui.utils.append
import com.quitr.snac.core.ui.utils.fadePlaceholder

@Composable
fun EpisodeDetailsScreen(
    onPersonCardTap: (id: Int) -> Unit,
    onGuestStarExpand: () -> Unit,
    onCrewExpand: () -> Unit,
    onBackPressed: () -> Unit,
    episode: EpisodeDetails,
) {
        with(episode) {
                ResizableHeaderScaffold(
                    title = name,
                    onBackPressed = onBackPressed,
                    expandedContent = {
                        Box(
                            Modifier
                                .height(376.dp)
                                .fillMaxWidth()
                        ) {
                            val backgroundColor = MaterialTheme.colorScheme.surface
                            AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(stillUrl)
                                .crossfade(true).build(),
                                placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                                error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                                contentDescription = stringResource(
                                    R.string.backdrop_description,
                                    name
                                ),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .aspectRatio(16f / 9f)
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
                            Column(
                                Modifier
                                    .align(Alignment.BottomStart)
                                    .fillMaxWidth()
                                    .padding(16f.dp),
                                verticalArrangement = Arrangement.spacedBy(8f.dp)
                            ) {
                                with(MaterialTheme.typography) {
                                    Text(name, style = titleLarge)

                                    Text(
                                        listOf(
                                            airDate,
                                            pluralStringResource(R.plurals.minutes_short, runtime, runtime)
                                        ).filter(
                                            String::isNotBlank
                                        )//TODO extract to string
                                            .joinToString(" • "),
                                        style = titleSmall
                                    )

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
                ) {
                    separator()
                    if(overview.isNotEmpty()) {
                        item {
                            Overview(overview = overview, modifier = Modifier.padding(horizontal = 16f.dp))
                        }
                        separator()
                    }
                    if(guestStars.isNotEmpty()) {
                        item {
                            PersonCarousel(
                                category = stringResource(R.string.cast),
                                people = guestStars,
                                onExpand = onGuestStarExpand,
                                onPersonClicked = onPersonCardTap
                            )
                        }
                        item { Spacer(Modifier.height(16f.dp)) }
                    }
                    if (crew.isNotEmpty()) {
                        item {
                            PersonCarousel(
                                category = stringResource(R.string.crew),
                                people = crew,
                                onExpand = onCrewExpand,
                                onPersonClicked = onPersonCardTap
                            )
                        }
                        item { Spacer(Modifier.height(16f.dp)) }
                    }
                }
            }

}

@Composable
fun EpisodeScreenPlaceholder(
    modifier: Modifier = Modifier, onBackPressed: () -> Unit,
) {
    ResizableHeaderScaffold(
        modifier,
        title = "",
        onBackPressed = onBackPressed,
        expandedContent = {
            Box(
                Modifier
                    .height(376.dp)
                    .fillMaxWidth()
            ) {
                val backgroundColor = MaterialTheme.colorScheme.surface
                val variant = MaterialTheme.colorScheme.surfaceVariant
                Box(modifier = Modifier
                    .height(220f.dp)
                    .fillMaxWidth()
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(variant, backgroundColor),
                            startY = size.height / 5,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient)
                        }
                    })
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(16f.dp),
                    verticalArrangement = Arrangement.spacedBy(8f.dp)
                ) {
                    Box(
                        Modifier
                            .height(24f.dp)
                            .fillMaxWidth(0.4f)
                            .fadePlaceholder()
                    )
                    repeat(3) {
                        Box(
                            Modifier
                                .height(16f.dp)
                                .fillMaxWidth(0.3f)
                                .fadePlaceholder()
                        )
                    }
                }
            }
        },
    ) {
        item {
            Column(
                Modifier
                    .padding(horizontal = 16f.dp, vertical = 24f.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8f.dp)
            ) {
                Box(
                    Modifier
                        .height(24f.dp)
                        .fillMaxWidth(0.3f)
                        .fadePlaceholder()
                )
                repeat(4) {
                    Box(
                        Modifier
                            .height(16f.dp)
                            .fillMaxWidth()
                            .fadePlaceholder()
                    )
                }
                repeat(2) {
                    Spacer(Modifier.height(4f.dp))
                    Box(
                        Modifier
                            .height(24f.dp)
                            .fillMaxWidth(0.3f)
                            .fadePlaceholder()
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8f.dp)
                    ) {
                        repeat(3) {
                            Box(
                                Modifier
                                    .weight(1f)
                                    .aspectRatio(3f/5f)
                                    .fadePlaceholder()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun EpisodeScreenPreview() {
    EpisodeDetailsScreen(
        onPersonCardTap = {},
        onGuestStarExpand = { /*TODO*/ },
        onCrewExpand = { /*TODO*/ },
        onBackPressed = {},
        episode = FakeEpisode
    )
}