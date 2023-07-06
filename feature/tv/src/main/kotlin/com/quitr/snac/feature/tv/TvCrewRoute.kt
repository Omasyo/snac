package com.quitr.snac.feature.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.ui.show.ShowCreditsScreen


 object TvCrewRoute : NavigationRoute("tv/%s/crew") {
    const val tvId = "tvId"

//    override val root = "tv/crew"

    override val requiredArguments: List<String> = listOf(tvId)


//    fun route(id: Int) = route(
//        mapOf(tvId to id)
//    )
}

@Composable
fun TvCrewRoute(
    modifier: Modifier = Modifier,
    onPersonCardTapped: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: TvDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.tvDetailsUiState.collectAsState().value
    if (state is TvScreenUiState.Success) {
        ShowCreditsScreen(
            modifier,
            title = stringResource(R.string.crew),
            onPersonCardTap = onPersonCardTapped,
            onBackPressed = onBackPressed,
            people = state.tv.crew
        )
    }
}
