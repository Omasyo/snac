package com.keetr.snac.feature.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.keetr.snac.core.common.R
import com.keetr.snac.core.model.Person
import com.keetr.snac.core.model.Show
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.ui.ErrorScreen
import com.keetr.snac.core.ui.SnacClapper
import com.keetr.snac.core.ui.card.PersonCard
import com.keetr.snac.core.ui.card.ShowCard
import com.keetr.snac.core.ui.theme.SnacIcons

@Composable
fun SnacSearch(
    modifier: Modifier = Modifier,
    onMovieCardTap: (Int) -> Unit,
    onTvCardTap: (Int) -> Unit,
    onPersonCardTap: (Int) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    SnacSearchBar(
        modifier = modifier
            .testTag("loader")
            .fillMaxWidth()
            .padding(bottom = 8f.dp)
            .zIndex(1f),
        queryProvider = { viewModel.query.value },
        onQueryChange = viewModel::updateQuery,
        active = viewModel.active.value,
        onActiveChange = viewModel::updateActiveStatus,
        onClear = viewModel::clearQuery,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onPersonCardTap = onPersonCardTap,
        onRetry = viewModel::refresh,
        pagingItems = viewModel.searchResults.collectAsLazyPagingItems()
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SnacSearchBar(
    modifier: Modifier = Modifier,
    queryProvider: () -> String,
    onQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onClear: () -> Unit,
//    onSearch: (String) -> Unit,
    onMovieCardTap: (Int) -> Unit,
    onTvCardTap: (Int) -> Unit,
    onPersonCardTap: (Int) -> Unit,
    onRetry: () -> Unit,
    pagingItems: LazyPagingItems<Any>
) {
    Box(modifier) {
        SearchBar(
            modifier = Modifier.align(Alignment.Center),
            query = queryProvider(),
            onQueryChange = onQueryChange,
            onSearch = {},
            active = active,
            onActiveChange = onActiveChange,
            placeholder = { Text(stringResource(R.string.search_shows)) },
            leadingIcon = {
                AnimatedContent(
                    targetState = active,
                    contentAlignment = Alignment.Center,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(150, 150)) with
                                fadeOut(animationSpec = tween(150))
                    }, label = ""
                ) { active ->
                    when {
                        active -> IconButton(onClick = { onActiveChange(false) }) {
                            Icon(SnacIcons.ArrowBack, contentDescription = null)
                        }

                        else -> Icon(SnacIcons.Search, contentDescription = null)
                    }
                }

            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = queryProvider().isNotBlank(),
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    IconButton(onClick = onClear) {
                        Icon(SnacIcons.Clear, contentDescription = null)
                    }
                }
            }
        ) {
            when (pagingItems.loadState.refresh) {
                is LoadState.Error -> {

                }
                LoadState.Loading -> {
                    Box(Modifier.fillMaxSize()) {
                        SnacClapper(
                            Modifier
                                .size(200f.dp, 200f.dp)
                                .align(Alignment.Center))
                    }
                }

                is LoadState.NotLoading -> {
                    LazyVerticalGrid(
                        modifier = Modifier.testTag("search_results").fillMaxSize(),
                        columns = GridCells.Adaptive(96f.dp),
                        contentPadding = PaddingValues(16f.dp),
                        horizontalArrangement = Arrangement.spacedBy(8f.dp),
                        verticalArrangement = Arrangement.spacedBy(16f.dp),
                    ) {
                        items(
                            pagingItems.itemCount,
                            pagingItems.itemKey { it.toString() + it.hashCode() }) {
                            when (val result = pagingItems[it]!!) {
                                is Show -> ShowCard(
                                    Modifier.aspectRatio(3f / 5f),
                                    title = result.title,
                                    rating = result.rating,
                                    posterUrl = result.posterUrl,
                                    onClick = {
                                        when (result.showType) {
                                            ShowType.Movie -> {
                                                onMovieCardTap(result.id)
                                            }

                                            ShowType.Tv -> {
                                                onTvCardTap(result.id)
                                            }
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
                                    SnacClapper(
                                        modifier = Modifier
                                            .fillMaxSize()
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