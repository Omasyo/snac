package com.quitr.snac.core.ui.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder

fun Modifier.fadePlaceholder() = composed { this.then(
    Modifier.placeholder(
        true,
        color = MaterialTheme.colorScheme.inverseOnSurface,
        highlight = PlaceholderHighlight.fade(
            MaterialTheme.colorScheme.surfaceVariant,
        )
    )
) }