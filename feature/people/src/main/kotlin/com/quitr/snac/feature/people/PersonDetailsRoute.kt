package com.quitr.snac.feature.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quitr.snac.core.model.NavigationRoute

const val personId = "person-id"

object PersonDetailsRoute : NavigationRoute("person/%s") {
    override val requiredArguments: List<String>
        get() = listOf(personId)
}


fun NavController.navigateToPerson(personId: Int) =
    navigate(PersonDetailsRoute.route(personId))

fun NavGraphBuilder.personRoute(
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onActingCreditsExpand: (id: Int) -> Unit,
    onOtherCreditsExpand: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = PersonDetailsRoute.route,
    arguments = listOf(navArgument(personId) {
        type = NavType.IntType
    })
) {backStackEntry ->
    val personId =
        checkNotNull(backStackEntry.arguments?.getInt(personId))

   PersonDetailsRoute(
       onMovieCardTap = onMovieCardTap,
       onTvCardTap = onTvCardTap,
       onActingCreditsExpand = { onActingCreditsExpand(personId) },
       onOtherCreditsExpand = { onOtherCreditsExpand(personId) },
       onBackPressed = onBackPressed)
}


@Composable
fun PersonDetailsRoute(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onActingCreditsExpand: () -> Unit,
    onOtherCreditsExpand: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: PersonDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.personDetailsUiState.collectAsState()
    PeopleScreen(
        modifier = modifier,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed,
        onActingCreditsExpand = onActingCreditsExpand,
        onOtherCreditsExpand = onOtherCreditsExpand,
        uiState = uiState
    )
}
