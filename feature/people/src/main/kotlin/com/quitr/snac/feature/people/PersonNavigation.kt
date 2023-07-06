package com.quitr.snac.feature.people

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.NavigationRoute

internal const val PersonIdArg = "person-id"

private object PersonDetailsRoute : NavigationRoute("person/%s") {
    override val requiredArguments: List<String> = listOf(PersonIdArg)
}

private object PersonCrewRoute : NavigationRoute("person/%s/other_roles") {
    override val requiredArguments: List<String> = listOf(PersonIdArg)
}

private object PersonCastRoute : NavigationRoute("person/%s/acting-roles") {
    override val requiredArguments: List<String> = listOf(PersonIdArg)

}

fun NavController.navigateToPersonDetails(personId: Int) = navigate(PersonDetailsRoute.route(personId))

fun NavController.navigateToPersonCast(personId: Int) = navigate(PersonCastRoute.route(personId))

fun NavController.navigateToPersonCrew(personId: Int) = navigate(PersonCrewRoute.route(personId))


fun NavGraphBuilder.personDetailsRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onActingCreditsExpand: (id: Int) -> Unit,
    onOtherCreditsExpand: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = PersonDetailsRoute.route, arguments = listOf(navArgument(PersonIdArg) {
        type = NavType.IntType
    })
) { backStackEntry ->
    val personId = checkNotNull(backStackEntry.arguments?.getInt(PersonIdArg))

    PersonDetailsRoute(
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onActingCreditsExpand = { onActingCreditsExpand(personId) },
        onOtherCreditsExpand = { onOtherCreditsExpand(personId) },
        onBackPressed = onBackPressed
    )
}

fun NavGraphBuilder.personCastRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = PersonCastRoute.route, arguments = listOf(navArgument(PersonIdArg) {
        type = NavType.IntType
    })
) {
    PersonCastRoute(
        onMovieCardTap = onMovieCardTap, onTvCardTap = onTvCardTap, onBackPressed = onBackPressed
    )
}

fun NavGraphBuilder.personCrewRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = PersonCrewRoute.route, arguments = listOf(navArgument(PersonIdArg) {
        type = NavType.IntType
    })
) {
    PersonCrewRoute(
        onMovieCardTap = onMovieCardTap, onTvCardTap = onTvCardTap, onBackPressed = onBackPressed
    )
}