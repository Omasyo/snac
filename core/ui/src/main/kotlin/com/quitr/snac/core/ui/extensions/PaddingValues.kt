package com.quitr.snac.core.ui.extensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.LayoutDirection

operator fun PaddingValues.plus(other: PaddingValues) = PaddingValues(

    this.calculateStartPadding(LayoutDirection.Ltr) + other.calculateStartPadding(
        LayoutDirection.Ltr
    ),
    this.calculateTopPadding() + other.calculateTopPadding(),
    this.calculateEndPadding(LayoutDirection.Ltr) + other.calculateEndPadding(LayoutDirection.Ltr),
    this.calculateBottomPadding() + other.calculateBottomPadding()
)