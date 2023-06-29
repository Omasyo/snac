package com.quitr.snac.feature.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.model.NavigationRoute

object MovieDetailsRoute : NavigationRoute() {
    const val movieId = "movieId"

    override val requiredArguments: List<String>
        get() = listOf(movieId)

    override val root: String
        get() = "movie"

    fun route(id: Int) = route(
        mapOf(movieId to id)
    )
}

@Composable
fun MovieDetailsRoute(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onPersonCardTap: (id: Int) -> Unit,
    onCastExpand: () -> Unit,
    onCrewExpand: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.movieDetailsUiState.collectAsState()
    MovieDetailsScreen(
        modifier = modifier,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed,
        onCastExpand = onCastExpand,
        onCrewExpand = onCrewExpand,
        uiState = uiState
    )
}
