package com.quitr.snac.core.ui.show

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.ui.ResizableHeaderScaffold
import com.quitr.snac.core.ui.utils.fadePlaceholder

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowDetailsPlaceholder(
    modifier: Modifier = Modifier, onBackPressed: () -> Unit
) {
    ResizableHeaderScaffold(
        modifier,
        title = "",
        onBackPressed = onBackPressed,
        expandedContent = {
            Box(
                Modifier
                    .height(376.dp)
                    .fillMaxWidth()
            ) {
                val backgroundColor = MaterialTheme.colorScheme.surface
                val variant = MaterialTheme.colorScheme.surfaceVariant
                Box(modifier = Modifier
                    .height(220f.dp)
                    .fillMaxWidth()
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(variant, backgroundColor),
                            startY = size.height / 5,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient)
                        }
                    })
                Row(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(16f.dp)
                ) {
                    ElevatedCard(Modifier.size(120f.dp, 176f.dp)) {

                    }
                    Spacer(Modifier.width(24f.dp))
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        verticalArrangement = Arrangement.spacedBy(8f.dp)
                    ) {
                        Box(
                            Modifier
                                .height(24f.dp)
                                .fillMaxWidth(0.4f)
                                .fadePlaceholder()
                        )
                        repeat(3) {
                            Box(
                                Modifier
                                    .height(16f.dp)
                                    .fillMaxWidth(0.6f)
                                    .fadePlaceholder()
                            )
                        }
                    }
                }
            }
        },
    ) {
        item {
            Column(
                Modifier
                    .padding(horizontal = 16f.dp, vertical = 24f.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8f.dp)
            ) {
                Box(
                    Modifier
                        .height(24f.dp)
                        .fillMaxWidth()
                        .fadePlaceholder()
                )
                Spacer(Modifier.height(4f.dp))
                FlowRow(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16f.dp),
                ) {
                    repeat(12) {
                        Box(
                            Modifier
                                .height(36f.dp)
                                .fillMaxWidth(if (it % 2 == 0) .3f else .2f)
                                .fadePlaceholder()
                        )
                    }
                }
                Spacer(Modifier.height(4f.dp))
                Box(
                    Modifier
                        .height(24f.dp)
                        .fillMaxWidth(0.3f)
                        .fadePlaceholder()
                )
                repeat(3) {
                    Box(
                        Modifier
                            .height(16f.dp)
                            .fillMaxWidth()
                            .fadePlaceholder()
                    )
                }
                Spacer(Modifier.height(4f.dp))
                Box(
                    Modifier
                        .height(24f.dp)
                        .fillMaxWidth(0.3f)
                        .fadePlaceholder()
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8f.dp)
                ) {
                    repeat(3) {
                        Box(
                            Modifier
                                .weight(1f)
                                .aspectRatio(3f/5f)
                                .fadePlaceholder()
                        )
                    }
                }
            }
        }
    }
}