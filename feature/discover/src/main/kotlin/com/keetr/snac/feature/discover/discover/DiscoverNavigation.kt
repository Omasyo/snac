package com.keetr.snac.feature.discover.discover

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.keetr.snac.core.model.NavigationRoute
import com.keetr.snac.core.model.SectionType

private object DiscoverRoute : NavigationRoute("discover")

fun NavController.navigateToDiscover(navOptions: NavOptions? = null) =
    navigate(DiscoverRoute.route, navOptions)

fun NavGraphBuilder.discoverRoute(
    onCarouselExpand: (SectionType) -> Unit,
    onMovieCardClicked: (id: Int) -> Unit,
    onTvCardClicked: (id: Int) -> Unit,
) = composable(DiscoverRoute.route) {
    DiscoverRoute(
        onCarouselExpand = onCarouselExpand,
        onMovieCardClicked = onMovieCardClicked,
        onTvCardClicked = onTvCardClicked
    )
}