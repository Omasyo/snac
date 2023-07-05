package com.quitr.snac.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.ui.theme.SnacIcons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResizableHeaderScaffold(
    modifier: Modifier = Modifier,
    title: String,
    onBackPressed: () -> Unit,
    fadeTitle: Boolean = true,
    expandedContent: @Composable (PaddingValues) -> Unit,
    content: LazyListScope.() -> Unit,
) {
    val lazyColumnState = rememberLazyListState()

    val isCollapsed by remember {
        derivedStateOf {
            lazyColumnState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(SnacIcons.ArrowBack, null)
                    }
                },
                title = {
                    AnimatedVisibility(
                        visible = !fadeTitle || isCollapsed, enter = fadeIn(), exit = fadeOut()
                    ) {
                        Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        val backGroundColor = MaterialTheme.colorScheme.surface
        LazyColumn(
            state = lazyColumnState, contentPadding = PaddingValues(bottom = 24f.dp)
        ) {
            item {
                expandedContent(innerPadding)
            }
            content()
        }
        Box(
            Modifier
                .drawBehind {
                    drawRect(if (isCollapsed) backGroundColor else Color.Transparent)
                }
                .statusBarsPadding()
                .height(64f.dp)
                .fillMaxWidth()

        )
    }
}