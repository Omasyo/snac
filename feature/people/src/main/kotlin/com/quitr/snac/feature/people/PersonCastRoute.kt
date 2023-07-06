package com.quitr.snac.feature.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun PersonCastRoute(
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
