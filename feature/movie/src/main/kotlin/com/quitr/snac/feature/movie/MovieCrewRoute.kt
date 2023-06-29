package com.quitr.snac.feature.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.ui.CreditsScreen


 object MovieCrewRoute : NavigationRoute() {
    const val movieId = "movieId"

    override val root = "movie/cast"

    override val requiredArguments: List<String> = listOf(movieId)

    fun route(id: Int) = route(
        mapOf(movieId to id)
    )
}

@Composable
fun MovieCrewRoute(
    modifier: Modifier = Modifier,
    onPersonCardTapped: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.movieDetailsUiState.collectAsState().value
    if (state is MovieDetailsUiState.Success) {
        CreditsScreen(
            modifier,
            title = stringResource(R.string.crew),
            onPersonCardTap = onPersonCardTapped,
            onBackPressed = onBackPressed,
            people = state.movie.crew
        )
    }
}
