package com.keetr.snac.feature.tv.season

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.common.R
import com.keetr.snac.core.model.Season
import com.keetr.snac.core.ui.card.SeasonCard
import com.keetr.snac.core.ui.theme.SnacIcons
import com.keetr.snac.core.ui.theme.SnacTheme
import com.keetr.snac.core.ui.utils.plus
import com.keetr.snac.feature.tv.fake.FakeTv

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SeasonScreen(
    modifier: Modifier = Modifier,
    tvId: Int,
    onSeasonCardTap: (tvId: Int, seasonNumber: Int) -> Unit,
    onBackPressed: () -> Unit,
    seasons: List<Season>
) {
    Scaffold(
        modifier,
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(SnacIcons.ArrowBack, null)
                }
            }, title = {
                Text(pluralStringResource(R.plurals.seasons, seasons.size))
            })
        }) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding + PaddingValues(16f.dp),
            verticalArrangement = Arrangement.spacedBy(16f.dp),
        ) {
            items(seasons, key = { season -> season.id }) { season ->
                SeasonCard(
                    modifier = Modifier.height(176f.dp),
                    title = season.name,
                    season = season.seasonNumber,
                    airDate = season.airDate,
                    episodeCount = season.episodeCount,
                    description = season.overview,
                    posterUrl = season.posterUrl,
                    onClick = { onSeasonCardTap(tvId, season.seasonNumber) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SeasonScreenPreview() {
    SnacTheme {
        SeasonScreen(
            tvId = 1519,
            onSeasonCardTap = { i: Int, i1: Int -> },
            onBackPressed = {},
            seasons = FakeTv.seasons
        )
    }
}