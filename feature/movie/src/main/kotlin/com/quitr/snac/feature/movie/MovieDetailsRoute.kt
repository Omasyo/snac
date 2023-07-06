package com.quitr.snac.feature.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.NavigationRoute

const val movieId = "movie-id"

internal object MovieDetailsRoute : NavigationRoute("movie/%s") {
    const val root = "movie"
    override val requiredArguments: List<String>
        get() = listOf(movieId)
}

fun NavController.navigateToMovie(movieId: Int, navOptions: NavOptions? = null) =
    navigate(MovieDetailsRoute.route(movieId), navOptions)

fun NavGraphBuilder.movieDetailsRoute(
    onMovieCardTap: (movieId: Int) -> Unit,
    onTvCardTap: (tvId: Int) -> Unit,
    onPersonCardTap: (personId: Int) -> Unit,
    onCastExpand: (movieId: Int) -> Unit,
    onCrewExpand: (movieId: Int) -> Unit,
    onBackPressed: () -> Unit,
) =  composable(
        route = MovieDetailsRoute.route,
        arguments = listOf(navArgument(movieId) {
            type = NavType.IntType
        })
    ) { backStackEntry ->

    val movieId = checkNotNull(backStackEntry.arguments?.getInt(movieId))

        MovieDetailsRoute(
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onPersonCardTap = onPersonCardTap,
            onCastExpand = { onCastExpand(movieId) },
            onCrewExpand = { onCrewExpand(movieId) },
            onBackPressed = onBackPressed,
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
