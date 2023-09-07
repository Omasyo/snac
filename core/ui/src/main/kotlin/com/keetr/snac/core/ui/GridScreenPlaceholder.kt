package com.keetr.snac.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.ui.utils.fadePlaceholder

@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GridScreenPlaceholder(modifier: Modifier = Modifier) {

    FlowRow(
        modifier, maxItemsInEachRow = 3,
        verticalArrangement = Arrangement.spacedBy(16f.dp),
        horizontalArrangement = Arrangement.spacedBy(8f.dp),
    ) {
        repeat(12) {
            Box(
                Modifier
                    .weight(1f)
                    .aspectRatio(3f / 5f)
                    .fadePlaceholder()
            )
        }
    }
}