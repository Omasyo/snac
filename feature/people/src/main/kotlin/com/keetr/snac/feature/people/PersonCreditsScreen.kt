package com.keetr.snac.feature.people

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.model.Credit
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.ui.card.CreditCard
import com.keetr.snac.core.ui.theme.SnacIcons
import com.keetr.snac.core.ui.utils.plus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PersonCreditsScreen(
    modifier: Modifier = Modifier,
    title: String,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    credits: List<Credit>,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(SnacIcons.ArrowBack, null)
                }
            }, title = {
                Text(title)
            })
        }) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(96f.dp),
            contentPadding = innerPadding + PaddingValues(16f.dp),
            horizontalArrangement = Arrangement.spacedBy(8f.dp),
            verticalArrangement = Arrangement.spacedBy(16f.dp),
        ) {
            items(credits, key = { credit -> credit.id }) { credit ->
                with(credit) {
                    CreditCard(
                        Modifier.size(120f.dp, 200f.dp),
                        title = credit.title,
                        role = role,
                        rating = rating,
                        posterUrl = posterUrl,
                        onClick = {
                            when (showType) {
                                ShowType.Movie -> onMovieCardTap(showId)
                                ShowType.Tv -> onTvCardTap(showId)
                            }
                        }
                    )
                }
            }
        }
    }
}