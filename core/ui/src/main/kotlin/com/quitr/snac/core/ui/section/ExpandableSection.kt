package com.quitr.snac.core.ui.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.ShowCard
import com.quitr.snac.core.ui.theme.SnacIcons


@Composable
fun ExpandableSection(
    modifier: Modifier = Modifier,
    header: @Composable RowScope.() -> Unit,
    onExpand: () -> Unit,
    content: LazyListScope.() -> Unit,
) = ExpandableSectionS(modifier = modifier, header = header, onExpand = onExpand) {
    LazyRow(
        Modifier
            .height(200f.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16f.dp),
        horizontalArrangement = Arrangement.spacedBy(8f.dp),
        content = content
    )
}

@Composable
fun ExpandableSectionS(
    modifier: Modifier = Modifier,
    header: @Composable RowScope.() -> Unit,
    onExpand: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        Row(
            Modifier
                .clickable(onClick = onExpand)
                .padding(horizontal = 16f.dp, vertical = 8f.dp)
                .fillMaxWidth()
                .height(40f.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            header()
            Spacer(Modifier.weight(1f))
            Icon(SnacIcons.ArrowForward, contentDescription = null)

        }
        content()
    }
}

@Preview
@Composable
fun SectionPlaceholder(modifier: Modifier = Modifier) {
    PlaceholderHighlight.shimmer()
    Column(modifier.padding(16f.dp)) {
        val sharedModifier = Modifier
            .fillMaxWidth()
            .placeholder(
                true, color = MaterialTheme.colorScheme.inverseOnSurface,
                highlight = PlaceholderHighlight.fade(
                    MaterialTheme.colorScheme.surfaceVariant,
                )
            )

        Box(
            sharedModifier
                .height(36f.dp)
        )
        Spacer(Modifier.height(16f.dp))
        Box(
            sharedModifier
                .height(176f.dp)
        )
    }
}

@Composable
fun SectionError(
    modifier: Modifier = Modifier,
    name: String,
    type: ShowType,
    onRetry: () -> Unit
) {
    Column(modifier) {
        Row(
            Modifier
                .padding(horizontal = 16f.dp, vertical = 12f.dp)
                .fillMaxWidth()
                .height(40f.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(name, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.width(4f.dp))
            TypeContainer(type)
            Spacer(Modifier.weight(1f))
            Icon(SnacIcons.ArrowForward, contentDescription = null)
        }
        Box(
            Modifier
                .height(200f.dp)
                .fillMaxWidth(),
        ) {
            OutlinedButton(onClick = onRetry, modifier = Modifier.align(Alignment.Center)) {
                Text(text = "Retry")
            }
        }
    }
}