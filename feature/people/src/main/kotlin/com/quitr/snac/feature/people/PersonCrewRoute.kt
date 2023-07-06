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

object PersonCrewRoute : NavigationRoute("person/%s/other_roles") {
    override val requiredArguments: List<String> = listOf(personId)
}


fun NavController.navigateToPersonCrew(personId: Int) =
    navigate(PersonCrewRoute.route(personId))

fun NavGraphBuilder.personCrewRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = PersonCrewRoute.route,
    arguments = listOf(navArgument(personId) {
        type = NavType.IntType
    })
) {
    PersonCrewRoute(
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed
    )
}

@Composable
fun PersonCrewRoute(
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
            title = stringResource(R.string.other_roles),
            onMovieCardTap = onMovieCardTap,
            onTvCardTap = onTvCardTap,
            onBackPressed = onBackPressed,
            credits = state.person.otherCredits
        )
    }
}
