package com.keetr.snac.core.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.keetr.snac.core.ui.R

object SnacIcons {
    val HomeOutlined = Icons.Outlined.Home
    val HomeFilled = Icons.Filled.Home
    val LibraryOutlined = Icons.Outlined.CollectionsBookmark
    val LibraryFilled = Icons.Filled.CollectionsBookmark
    val ArrowForward = Icons.Outlined.ArrowForward
    val ArrowBack = Icons.Outlined.ArrowBack
    val People = Icons.Filled.PeopleAlt
    val Clear = Icons.Outlined.Clear
    val Search = Icons.Outlined.Search

    val Star: Painter
        @Composable get() = painterResource(R.drawable.star)

}