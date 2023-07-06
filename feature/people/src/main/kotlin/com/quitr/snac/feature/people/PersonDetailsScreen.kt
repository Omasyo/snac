package com.quitr.snac.feature.people

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.ResizableHeaderScaffold
import com.quitr.snac.core.ui.show.AboutDetails
import com.quitr.snac.core.ui.show.separator
import com.quitr.snac.core.common.R as CommonR

@Composable
internal fun PeopleScreen(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onActingCreditsExpand: () -> Unit,
    onOtherCreditsExpand: () -> Unit,
    onBackPressed: () -> Unit,
    uiState: PersonDetailsUiState
) {
    fun showTapHandler(showType: ShowType, showId: Int) = when (showType) {
        ShowType.Movie -> onMovieCardTap(showId)
        ShowType.Tv -> onTvCardTap(showId)
    }

    when (uiState) {
        is PersonDetailsUiState.Error -> {
            // TODO
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16f.dp)
                    .padding(top = 36f.dp)
            ) {
                Text(
                    uiState.error.message ?: "", style =
                    MaterialTheme.typography.headlineMedium
                )
            }
        }

        PersonDetailsUiState.Loading -> PersonDetailsPlaceholder()
        is PersonDetailsUiState.Success -> with(uiState.person) {
            ResizableHeaderScaffold(
                modifier,
                title = name,
                onBackPressed = onBackPressed,
                expandedContent = {
                    Box(
                        Modifier
                            .height(360.dp)
                            .fillMaxWidth()
                    ) {
                        with(MaterialTheme.colorScheme) {
                            Box(modifier = Modifier
                                .height(220f.dp)
                                .fillMaxWidth()
                                .drawWithCache {
                                    val gradient = Brush.verticalGradient(
                                        colors = listOf(surfaceVariant, surface),
                                        startY = size.height / 5,
                                        endY = size.height
                                    )
                                    onDrawWithContent {
                                        drawContent()
                                        drawRect(gradient)
                                    }
                                })
                        }
                        Row(
                            Modifier
                                .align(Alignment.BottomStart)
                                .padding(16f.dp)
                        ) {
                            ElevatedCard(
                                Modifier
                                    .size(176.dp, 176f.dp)
                                    .clip(CircleShape)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(profilePath).crossfade(true).build(),
                                    placeholder = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                                    error = ColorPainter(MaterialTheme.colorScheme.surfaceVariant),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = stringResource(
                                        CommonR.string.profile_image, name
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
                                    Text(name, style = titleLarge)

                                    Text(knownForDepartment, style = titleSmall)
                                }
                            }
                        }
                    }
                },
            ) {
                //TODO Add discover
                separator()
                item {
                    Column(
                        Modifier.padding(horizontal = 16f.dp),
                        verticalArrangement = Arrangement.spacedBy(8f.dp)
                    ) {
//                    AboutDetails(info = "Age", detail = person)
                        if (birthday.isNotBlank()) AboutDetails(
                            info = stringResource(R.string.born_on),
                            detail = birthday,
                            infoRatio = 0.3f
                        )
                        if (deathday.isNotBlank()) AboutDetails(
                            info = stringResource(R.string.died_on),
                            detail = deathday,
                            infoRatio = 0.3f
                        )
                        if (placeOfBirth.isNotBlank()) AboutDetails(
                            info = stringResource(R.string.from),
                            detail = placeOfBirth,
                            infoRatio = 0.3f
                        )
                    }
                }
                if (biography.isNotBlank()) {
                    item { Spacer(Modifier.height(16f.dp)) }
                    item {
                        Column(Modifier.padding(horizontal = 16f.dp)) {
                            Text(
                                stringResource(R.string.biography),
                                color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                            )
                            Spacer(Modifier.height(4f.dp))
                            Text(
                                biography,
                            )
                        }
                    }
                }
                if (birthday.isNotBlank() || biography.isNotBlank() ||
                    placeOfBirth.isNotBlank() || deathday.isNotBlank()
                ) {
                    separator()
                }
                if (actingCredits.isNotEmpty()) {
                    item {
                        CreditCarousel(
                            category = stringResource(R.string.starred_in),
                            credits = actingCredits,
                            onExpand = onActingCreditsExpand,
                            onShowClicked = ::showTapHandler,
                        )
                    }
                }
                item { Spacer(Modifier.height(16f.dp)) }
                if (otherCredits.isNotEmpty()) {
                    item {
                        CreditCarousel(
                            category = stringResource(R.string.others),
                            credits = otherCredits,
                            onExpand = onOtherCreditsExpand,
                            onShowClicked = ::showTapHandler,
                        )
                    }
                }
            }
        }
    }
}