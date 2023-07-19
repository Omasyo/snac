package com.keetr.snac.feature.movie

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.keetr.snac.core.model.NavigationRoute

internal const val MovieIdArg = "movie-id"

private object MovieDetailsRoute : NavigationRoute("movie/%s") {
    override val requiredArguments: List<String> = listOf(MovieIdArg)
}

private object MovieCastRoute : NavigationRoute("movie/%s/cast") {

    override val requiredArguments: List<String> = listOf(MovieIdArg)
}

private object MovieCrewRoute : NavigationRoute("movie/%s/crew") {
    override val requiredArguments: List<String> = listOf(MovieIdArg)
}

fun NavController.navigateToMovieDetails(movieId: Int, navOptions: NavOptions? = null) =
    navigate(MovieDetailsRoute.route(movieId), navOptions)

fun NavController.navigateToMovieCast(movieId: Int, navOptions: NavOptions? = null) =
    navigate(MovieCastRoute.route(movieId), navOptions)

fun NavController.navigateToMovieCrew(movieId: Int, navOptions: NavOptions? = null) =
    navigate(MovieCrewRoute.route(movieId), navOptions)

fun NavGraphBuilder.movieDetailsRoute(
    onMovieCardTap: (movieId: Int) -> Unit,
    onTvCardTap: (tvId: Int) -> Unit,
    onPersonCardTap: (personId: Int) -> Unit,
    onCastExpand: (movieId: Int) -> Unit,
    onCrewExpand: (movieId: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = MovieDetailsRoute.route,
    arguments = listOf(navArgument(MovieIdArg) {
        type = NavType.IntType
    })
) { backStackEntry ->

    val movieId = checkNotNull(backStackEntry.arguments?.getInt(MovieIdArg))

    MovieDetailsRoute(
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onPersonCardTap = onPersonCardTap,
        onCastExpand = { onCastExpand(movieId) },
        onCrewExpand = { onCrewExpand(movieId) },
        onBackPressed = onBackPressed,
    )
}

fun NavGraphBuilder.movieCastRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) =  composable(
    route = MovieCastRoute.route,
    arguments = listOf(navArgument(MovieIdArg) {
        type = NavType.IntType
    })
) {
    MovieCastRoute(
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed,
    )
}

fun NavGraphBuilder.movieCrewRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) =  composable(
    route = MovieCrewRoute.route,
    arguments = listOf(navArgument(MovieIdArg) {
        type = NavType.IntType
    })
) {
    MovieCrewRoute(
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed,
    )
}
