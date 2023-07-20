package com.keetr.snac.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.ui.utils.fadePlaceholder

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GridScreenPlaceholder(modifier: Modifier = Modifier) {

    //TODO use arrangement: https://issuetracker.google.com/issues/268365538
    FlowRow(
        modifier, maxItemsInEachRow = 3
    ) {
        repeat(12) {
            Box(
                Modifier
                    .weight(1f)
                    .aspectRatio(3f / 5f)
                    .padding(horizontal = 4f.dp, vertical = 8f.dp)
                    .fadePlaceholder()
            )
        }
    }
}