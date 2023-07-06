package com.quitr.snac.feature.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.quitr.snac.core.model.NavigationRoute

object PersonDetailsRoute : NavigationRoute() {
    const val personId = "personId"

    override val requiredArguments: List<String>
        get() = listOf(personId)
    override val format: String
        get() = "person/%s"

//    override val root: String
//        get() = "person"

//    fun route(id: Int) = route(
//        mapOf(personId to id)
//    )
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
