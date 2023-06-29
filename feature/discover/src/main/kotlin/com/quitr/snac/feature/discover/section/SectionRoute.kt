package com.quitr.snac.feature.discover.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.feature.discover.discover.title

object SectionRoute : NavigationRoute() {
    const val sectionType = "section-type"

    override val root = "section"

    override val requiredArguments: List<String> = listOf(sectionType)

    fun route(type: SectionType) = route(
        mapOf(sectionType to type)
    )
}

@Composable
fun SectionRoute(
    modifier: Modifier = Modifier,
    onMovieCardTap: (id: Int) -> Unit,
    onTvCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SectionScreenViewModel = hiltViewModel()
) {
    SectionScreen(
        modifier,
        title = viewModel.sectionType.title,
        onMovieCardTap = onMovieCardTap,
        onTvCardTap = onTvCardTap,
        onBackPressed = onBackPressed,
        pagingItems = viewModel.shows.collectAsLazyPagingItems()
    )
}
