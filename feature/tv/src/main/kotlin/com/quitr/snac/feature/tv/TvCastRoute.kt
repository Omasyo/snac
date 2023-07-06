package com.quitr.snac.feature.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.common.R
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.ui.show.ShowCreditsScreen

object TvCastRoute : NavigationRoute("tv/%s/cast") {
    const val tvId = "tvId"

//    override val root = "tv/cast"

    override val requiredArguments: List<String> = listOf(tvId)


//    fun route(id: Int) = route(
//        mapOf(tvId to id)
//    )
}

@Composable
fun TvCastRoute(
    modifier: Modifier = Modifier,
    onPersonCardTapped: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: TvDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.tvDetailsUiState.collectAsState().value
    if (state is TvScreenUiState.Success) {
        ShowCreditsScreen(
            modifier,
            title = stringResource(R.string.cast),
            onPersonCardTap = onPersonCardTapped,
            onBackPressed = onBackPressed,
            people = state.tv.cast
        )
    }
}
