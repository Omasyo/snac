package com.quitr.snac.feature.discover.section

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.model.SectionType


internal const val SectionTypeArg = "section-type"

private object SectionRoute : NavigationRoute("section/%s") {
    override val requiredArguments: List<String> = listOf(SectionTypeArg)
}

fun NavController.navigateToSection(sectionType: SectionType, navOptions: NavOptions? = null) =
    navigate(SectionRoute.route(sectionType), navOptions)

fun NavGraphBuilder.sectionRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = SectionRoute.route,
    arguments = listOf(navArgument(SectionTypeArg) {
        type = NavType.EnumType(SectionType::class.java)
    })
) {
    SectionRoute(
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed
    )
}