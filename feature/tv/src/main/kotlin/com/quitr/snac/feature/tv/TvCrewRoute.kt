package com.quitr.snac.feature.tv

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
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.ui.show.ShowCreditsScreen


 object TvCrewRoute : NavigationRoute("tv/%s/crew") {
    override val requiredArguments: List<String> = listOf(tvId)
}


fun NavController.navigateTvCrew(showId: Int) =
    navigate(EpisodeDetailsRoute.route(showId))

fun NavGraphBuilder.tvCrewRoute(
    onPersonCardTap: (personId: Int) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = TvCrewRoute.route,
    arguments = listOf(
        navArgument(tvId) { type = NavType.IntType }
    )
) {
    TvCrewRoute(
        onPersonCardTap = onPersonCardTap,
        onBackPressed = onBackPressed
    )
}

@Composable
fun TvCrewRoute(
    modifier: Modifier = Modifier,
    onPersonCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: TvDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.tvDetailsUiState.collectAsState().value
    if (state is TvScreenUiState.Success) {
        ShowCreditsScreen(
            modifier,
            title = stringResource(R.string.crew),
            onPersonCardTap = onPersonCardTap,
            onBackPressed = onBackPressed,
            people = state.tv.crew
        )
    }
}
