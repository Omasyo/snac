package com.quitr.snac.feature.tv.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.common.R
import com.quitr.snac.core.ui.show.ShowCreditsScreen

@Composable
internal fun TvCastRoute(
    modifier: Modifier = Modifier,
    onPersonCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: TvDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.tvDetailsUiState.collectAsState().value
    if (state is TvScreenUiState.Success) {
        ShowCreditsScreen(
            modifier,
            title = stringResource(R.string.cast),
            onPersonCardTap = onPersonCardTap,
            onBackPressed = onBackPressed,
            people = state.tv.cast
        )
    }
}
