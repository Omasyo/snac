package com.quitr.snac.feature.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.NavigationRoute

object PersonCastRoute : NavigationRoute("person/%s/acting-roles") {
    override val requiredArguments: List<String> = listOf(personId)

}


fun NavController.navigateToPersonCast(personId: Int) =
    navigate(PersonCastRoute.route(personId))

fun NavGraphBuilder.personCastRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = PersonCastRoute.route,
    arguments = listOf(navArgument(personId) {
        type = NavType.IntType
    })
) {
    PersonCastRoute(
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed)
}

@Composable
fun PersonCastRoute(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: PersonDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.personDetailsUiState.collectAsState().value
    if (state is PersonDetailsUiState.Success) {
        PersonCreditsScreen(
            modifier,
            title = stringResource(R.string.acting_roles),
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onBackPressed = onBackPressed,
            credits = state.person.actingCredits
        )
    }
}
