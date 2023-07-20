package com.keetr.snac.core.ui.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.ui.theme.SnacIcons
import com.keetr.snac.core.ui.utils.fadePlaceholder

@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    header: @Composable RowScope.() -> Unit,
    onExpand: () -> Unit,
    content: LazyListScope.() -> Unit,
) = Carousel(
    modifier = modifier,
    content = content,
    header = {
        Row(
            Modifier
                .clickable(onClick = onExpand)
                .padding(horizontal = 16f.dp, vertical = 4f.dp)
                .fillMaxWidth()
                .height(40f.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            header()
            Spacer(Modifier.weight(1f))
            Icon(SnacIcons.ArrowForward, contentDescription = null)
        }
    })


@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    content: LazyListScope.() -> Unit,
) {
    Column(modifier) {
        header()
        LazyRow(
            Modifier
                .height(200f.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16f.dp),
            horizontalArrangement = Arrangement.spacedBy(8f.dp),
            content = content
        )
    }
}

@Preview
@Composable
fun SectionPlaceholder(modifier: Modifier = Modifier) {
    PlaceholderHighlight.shimmer()
    Column(modifier.padding(16f.dp)) {
        val sharedModifier = Modifier
            .fillMaxWidth()
            .fadePlaceholder()

        Box(
            sharedModifier.height(36f.dp)
        )
        Spacer(Modifier.height(16f.dp))
        Box(
            sharedModifier.height(176f.dp)
        )
    }
}

@Composable
fun SectionError(
    modifier: Modifier = Modifier, name: String, type: ShowType, onRetry: () -> Unit
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
        Column(
            Modifier
                .height(200f.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("An error occured", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8f.dp))
            FilledTonalButton(onClick = onRetry) {
                Text(text = "Retry", modifier = Modifier.padding(horizontal = 16f.dp))
            }
        }
    }
}