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
import com.quitr.snac.core.model.Gender
import com.quitr.snac.core.model.PersonDetails
import com.quitr.snac.core.ui.AboutDetails
import com.quitr.snac.core.ui.InlineText
import com.quitr.snac.core.ui.ResizableHeaderScaffold
import com.quitr.snac.core.ui.append
import com.quitr.snac.core.ui.separator
import com.quitr.snac.core.ui.theme.SnacIcons

@Preview
@Composable
fun PeopleScreen(person: PersonDetails = Person) {
    with(person) {
        ResizableHeaderScaffold(
            title = name,
            onBackPressed = { /*TODO*/ },
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
                                model = ImageRequest.Builder(LocalContext.current).data(profilePath)
                                    .crossfade(true).build(),
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
                Column(Modifier.padding(horizontal = 16f.dp)) {
//                    AboutDetails(info = "Age", detail = person)
                    AboutDetails(info = "Born on", detail = birthday)
                    AboutDetails(info = "Died on", detail = deathday)
                    AboutDetails(info = "From", detail = placeOfBirth)
                }
            }
            item { Spacer(Modifier.height(16f.dp)) }
            item {
                Column(Modifier.padding(horizontal = 16f.dp)) {
                    Text(
                        "Biography",
                        color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                    )
                    Spacer(Modifier.height(8f.dp))
                    Text(
                        biography,
                    )
                }
            }
            separator()
        }
    }
}

val Person = PersonDetails(
    id = 7399,
    actingCredits = listOf(),
    adult = false,
    alsoKnownAs = listOf(),
    biography = "aenean",
    birthday = "volutpat",
    deathday = "ea",
    gender = Gender.Female,
    homepage = "nec",
    imdbId = "agam",
    knownForDepartment = "ius",
    name = "Marsha Byrd",
    otherCredits = listOf(),
    placeOfBirth = "nonumy",
    popularity = 0.1,
    profilePath = "dicunt"
)