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
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.Credit
import com.quitr.snac.core.model.Gender
import com.quitr.snac.core.model.PersonDetails
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.AboutDetails
import com.quitr.snac.core.ui.InlineText
import com.quitr.snac.core.ui.ResizableHeaderScaffold
import com.quitr.snac.core.ui.append
import com.quitr.snac.core.ui.separator
import com.quitr.snac.core.ui.theme.SnacIcons

@Composable
fun PeopleScreen(
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
        }

        PersonDetailsUiState.Loading -> {
            // TODO
        }

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
                        val backgroundColor = MaterialTheme.colorScheme.surface
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
                                        R.string.profile_image, name
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
                            info = "Born on", detail = birthday, infoRatio = 0.3f
                        )
                        if (deathday.isNotBlank()) AboutDetails(
                            info = "Died on", detail = deathday, infoRatio = 0.3f
                        )
                        if (placeOfBirth.isNotBlank()) AboutDetails(
                            info = "From", detail = placeOfBirth, infoRatio = 0.3f
                        )
                    }
                }
                if (biography.isNotBlank()) {
                    item { Spacer(Modifier.height(16f.dp)) }
                    item {
                        Column(Modifier.padding(horizontal = 16f.dp)) {
                            Text(
                                "Biography", color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                            )
                            Spacer(Modifier.height(4f.dp))
                            Text(
                                biography,
                            )
                        }
                    }
                }
                if (!(birthday.isBlank() || placeOfBirth.isBlank() || deathday.isBlank() || deathday.isBlank())) separator()
                if (actingCredits.isNotEmpty()) {
                    item {
                        CreditCarousel(
                            category = "Starred in",
                            credits = actingCredits,
                            onExpand = onActingCreditsExpand,
                            onShowClicked = ::showTapHandler,
                        )
                    }
                }
                if (otherCredits.isNotEmpty()) {
                    item {
                        CreditCarousel(
                            category = "Others",
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

val Credits = List(15) {
    Credit(
        id = "$it",
        role = "FisherMan",
        title = "Call of Hunger",
        rating = "10.0",
        posterUrl = "https://duckduckgo.com/?q=arcu",
        showType = ShowType.Tv,
        relevance = 2.3,
        showId = 6152
    )
}

val Person = PersonDetails(
    id = 7399,
    actingCredits = Credits,
    adult = false,
    alsoKnownAs = listOf(),
    biography = "After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse, where he encounters the Spider Society, a team of Spider-People charged with protecting the Multiverse’s very existence. But when the heroes clash on how to handle a new threat, Miles finds himself pitted against the other Spiders and must set out on his own to save those he loves most.",
    birthday = "27 November 1940",
    deathday = "27 November 1940",
    gender = Gender.Female,
    homepage = "nec",
    imdbId = "agam",
    knownForDepartment = "Actor",
    name = "Marsha Byrd",
    otherCredits = Credits,
    placeOfBirth = "Amasiri, Afikpo North, Ebonyi",
    popularity = 0.1,
    profilePath = "dicunt"
)
