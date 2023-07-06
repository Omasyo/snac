package com.quitr.snac.feature.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.ui.show.ShowCreditsScreen

internal object MovieCastRoute : NavigationRoute("movie/%s/cast") {

    override val requiredArguments: List<String> = listOf(movieId)
}


fun NavController.navigateToMovieCast(movieId: Int, navOptions: NavOptions? = null) =
    navigate(MovieCastRoute.route(movieId), navOptions)

fun NavGraphBuilder.movieCastRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) =  composable(
    route = MovieCastRoute.route,
    arguments = listOf(navArgument(movieId) {
        type = NavType.IntType
    })
) {
    MovieCastRoute(
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed,
    )
}

@Composable
private fun MovieCastRoute(
    modifier: Modifier = Modifier,
    onPersonCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.movieDetailsUiState.collectAsState().value
    if (state is MovieDetailsUiState.Success) {
        ShowCreditsScreen(
            modifier,
            title = stringResource(R.string.cast),
            onPersonCardTap = onPersonCardTap,
            onBackPressed = onBackPressed,
            people = state.movie.cast
        )
    }
}
