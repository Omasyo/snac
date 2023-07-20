package com.keetr.snac.feature.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun PersonDetailsRoute(
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
        onRetry = viewModel::refresh,
        uiState = uiState
    )
}
