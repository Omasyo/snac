package com.quitr.snac.feature.movie

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.ui.SectionScreen

object RecommendationsRoute : NavigationRoute() {
    const val movieId = "movieId"

    override val root = "movie/recommendations"

    override val requiredArguments: List<String> = listOf(movieId)

    fun route(id: Int) = route(
        mapOf(movieId to id)
    )
}

@Composable
fun RecommendationsRoute(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: RecommendationsScreenViewModel = hiltViewModel()
) {
    SectionScreen(
        modifier,
        title = "Recommendations",
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed,
        pagingItems = viewModel.shows.collectAsLazyPagingItems()
    )
}
