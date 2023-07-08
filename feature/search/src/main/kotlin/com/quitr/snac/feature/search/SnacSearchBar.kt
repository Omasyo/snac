package com.quitr.snac.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.itemKey
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.card.ShowCard
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.core.ui.utils.plus
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnacSearchBar() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxWidth()) {
        SearchBar(
            modifier = Modifier.align(Alignment.Center),
            query = query,
            onQueryChange = { query = it },
            onSearch = { },
            active = active,
            onActiveChange = { active = it }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(96f.dp),
                contentPadding = PaddingValues(16f.dp),
                horizontalArrangement = Arrangement.spacedBy(8f.dp),
                verticalArrangement = Arrangement.spacedBy(16f.dp),
            ) {
                items(42) {
//                    val show = pagingItems[it]!!
                    ShowCard(
                        Modifier.aspectRatio(3f / 5f),
                        title = "Title",
                        posterUrl = "show.posterUrl",
                        rating = "9.8",
                        onClick = {

                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SnacTheme {
        Scaffold(
            topBar = {
                SnacSearchBar()
            }
        ) {
            Box(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            )
        }
    }
}