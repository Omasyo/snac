package com.quitr.snac.feature.discover.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.feature.discover.discover.title

internal object SectionRoute : NavigationRoute("section/%s") {
    const val sectionType = "section-type"

    override val requiredArguments: List<String> = listOf(sectionType)
}

//private const val sectionRoute = "section"

fun NavController.navigateToSection(sectionType: SectionType, navOptions: NavOptions? = null) =
    navigate(SectionRoute.route(sectionType), navOptions)

fun NavGraphBuilder.sectionRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = SectionRoute.route,
    arguments = listOf(navArgument(SectionRoute.sectionType) {
        type = NavType.EnumType(SectionType::class.java)
    })
) {
    SectionRoute(
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun SectionRoute(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SectionScreenViewModel = hiltViewModel()
) {
    ShowGridScreen(
        modifier,
        title = viewModel.sectionType.title,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed,
        pagingItems = viewModel.shows.collectAsLazyPagingItems()
    )
}
