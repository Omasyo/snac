package com.quitr.snac.feature.search

import android.util.Log
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.quitr.snac.core.model.Person
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.card.PersonCard
import com.quitr.snac.core.ui.card.ShowCard
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.core.ui.utils.plus
import com.quitr.snac.feature.search.fake.FakeResults
import com.quitr.snac.feature.search.fake.FakeShow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

@Composable
fun SnacSearch(
    modifier: Modifier = Modifier,
    onMovieCardTap: (Int) -> Unit,
    onTvCardTap: (Int) -> Unit,
    onPersonCardTap: (Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    SnacSearchBar(
        modifier = modifier.fillMaxWidth(),
        query = viewModel.query.value,
        onQueryChange = viewModel::updateQuery,
        active = viewModel.active.value,
        onActiveChange = viewModel::updateActiveStatus,
        onClear = { /*TODO*/ },
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed,
        pagingItems = viewModel.searchResults.collectAsLazyPagingItems()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnacSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onClear: () -> Unit,
//    onSearch: (String) -> Unit,
    onMovieCardTap: (Int) -> Unit,
    onTvCardTap: (Int) -> Unit,
    onPersonCardTap: (Int) -> Unit,
    onBackPressed: () -> Unit,
    pagingItems: LazyPagingItems<Any>
) {
    Box(modifier) {
        SearchBar(
            modifier = Modifier.align(Alignment.Center),
            query = query,
            onQueryChange = onQueryChange,
            onSearch = {},
            active = active,
            onActiveChange = onActiveChange,
            leadingIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(SnacIcons.ArrowBack, contentDescription = null)
                }
            },
            trailingIcon = {
                IconButton(onClick = onClear) {
                    Icon(SnacIcons.Clear, contentDescription = null)
                }
            }
        ) {

            when (pagingItems.loadState.refresh) {
                is LoadState.Error -> { /*TODO*/ }
                LoadState.Loading -> {
                    CircularProgressIndicator()
                }
                is LoadState.NotLoading -> {

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(96f.dp),
                        contentPadding = PaddingValues(16f.dp),
                        horizontalArrangement = Arrangement.spacedBy(8f.dp),
                        verticalArrangement = Arrangement.spacedBy(16f.dp),
                    ) {

                        items(pagingItems.itemCount) {
                            when (val result = pagingItems[it]!!) {
                                is Show -> ShowCard(
                                    Modifier.aspectRatio(3f / 5f),
                                    title = result.title,
                                    rating = result.rating,
                                    posterUrl = result.posterUrl,
                                    onClick = {
                                        when (result.showType) {
                                            ShowType.Movie -> { onMovieCardTap(result.id) }
                                            ShowType.Tv -> { onTvCardTap(result.id) }
                                        }
                                    }
                                )

                                is Person -> PersonCard(
                                    Modifier.aspectRatio(3f / 5f),
                                    name = result.name,
                                    role = result.role,
                                    photoUrl = result.photoUrl,
                                    onClick = { onPersonCardTap(result.id) }
                                )
                                else -> Text(result.toString(), fontSize = 20f.sp)
                            }
                        }

                        if (pagingItems.loadState.append == LoadState.Loading) {
                            item {
                                Box(Modifier.aspectRatio(3f / 5f)) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

//@Preview
//@Composable
//fun SearchBarPreview() {
//
//    var query by remember { mutableStateOf("") }
//    var active by remember { mutableStateOf(false) }
//
//    SnacTheme {
//        Scaffold(
//            topBar = {
//                SnacSearchBar(
//                    modifier = Modifier.fillMaxWidth(),
//                    query = query,
//                    onQueryChange = { query = it },
//                    active = active,
//                    onActiveChange = { active = it },
//                    onClear = { query = "" },
////                    onSearch = {},
//                    onBackPressed = {  },
//                    pagingItems = flow {
//                        emit(PagingData.from(FakeResults))
//                    }.collectAsLazyPagingItems()
//                )
//            }
//        ) {
//            Box(
//                Modifier
//                    .padding(it)
//                    .fillMaxSize()
//            )
//        }
//    }
//}