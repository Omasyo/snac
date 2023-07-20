package com.keetr.snac.feature.tv.episode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.ui.GridScreenPlaceholder
import com.keetr.snac.core.ui.SnacClapper
import com.keetr.snac.core.ui.card.ShowCard
import com.keetr.snac.core.ui.theme.SnacIcons
import com.keetr.snac.core.ui.utils.plus
import com.keetr.snac.feature.tv.fake.FakeSeasonWithEpisodes
import com.keetr.snac.feature.tv.season.SeasonScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EpisodeScreen(
    modifier: Modifier = Modifier,
    tvId: Int,
    onEpisodeTap: (tvId: Int, seasonNumber: Int, episodeNumber: Int) -> Unit,
    onBackPressed: () -> Unit,
    uiState: SeasonScreenUiState
) {
    when (uiState) {
        is SeasonScreenUiState.Error -> TODO()
        SeasonScreenUiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                GridScreenPlaceholder(
                    Modifier
                        .padding(horizontal = 12f.dp, vertical = 16f.dp)
                )
            }
        }
        is SeasonScreenUiState.Success -> with(uiState.season) {
            Scaffold(
                modifier,
                topBar = {
                    TopAppBar(navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(SnacIcons.ArrowBack, null)
                        }
                    }, title = {
                        Text(name)
                    })
                }) { innerPadding ->
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(96f.dp),
                    contentPadding = innerPadding + PaddingValues(16f.dp),
                    horizontalArrangement = Arrangement.spacedBy(8f.dp),
                    verticalArrangement = Arrangement.spacedBy(16f.dp),
                ) {
                    items(episodes, key = { episode -> episode.id }) { episode ->
                        ShowCard(
                            modifier = Modifier.aspectRatio(3f / 5f),
                            title = episode.name,
                            posterUrl = episode.stillUrl,
                            rating = episode.voteAverage,
                            onClick = { onEpisodeTap(tvId , seasonNumber, episode.episodeNumber) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun EpisodeScreenPreview() {
    EpisodeScreen(
        tvId = 2,
        onEpisodeTap = { i: Int, i1: Int, i2: Int -> },
        onBackPressed = {},
        uiState = SeasonScreenUiState.Success(FakeSeasonWithEpisodes)
    )
}