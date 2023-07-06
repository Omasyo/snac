package com.quitr.snac.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.quitr.snac.R
import com.quitr.snac.core.ui.theme.SnacIcons

enum class NavBarScreens(
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    @StringRes val label: Int,
    val route: String
) {
    Discover(SnacIcons.HomeFilled, SnacIcons.HomeOutlined, R.string.discover, "discover"),
    Library(SnacIcons.LibraryFilled, SnacIcons.LibraryOutlined, R.string.library, "Library")
}