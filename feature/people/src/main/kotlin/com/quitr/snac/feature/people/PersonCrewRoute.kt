package com.quitr.snac.feature.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.model.NavigationRoute

object PersonCrewRoute : NavigationRoute() {
    const val personId = "personId"

    override val root = "person/other_roles"

    override val requiredArguments: List<String> = listOf(personId)

    fun route(id: Int) = route(
        mapOf(personId to id)
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
