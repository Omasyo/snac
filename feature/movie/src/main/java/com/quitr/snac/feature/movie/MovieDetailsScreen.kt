package com.quitr.snac.feature.movie

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.ui.theme.SnacIcons
import com.quitr.snac.core.ui.theme.SnacTheme
import kotlin.random.Random

@Composable
fun MovieRoute(
    modifier: Modifier = Modifier
) {
    MovieDetailsScreen(modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
) {
    val title = "Fantastic Beasts and How to Esacape Them"
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
                    IconButton(onClick = {}) {
                        Icon(SnacIcons.ArrowBack, null)
                    }
                },
                title = {
                    AnimatedVisibility(
                        visible = isCollapsed,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(title)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { innerPadding ->
        innerPadding.toString()
        LazyColumn(
            state = lazyColumnState
        ) {
            item {
                Box(
                    Modifier
                        .height(386.dp)
                        .fillMaxWidth()
                ) {
                    val backgroundColor = MaterialTheme.colorScheme.surface
                    Image(
                        painter = painterResource(com.quitr.snac.core.ui.R.drawable.poster_sample),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, backgroundColor),
                                    startY = size.height / 5,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient)
                                }
                            }
                    )
                    Row(
                        Modifier
                            .align(Alignment.BottomStart)
                            .padding(16f.dp)
                            .offset(0f.dp, 48f.dp)
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
                            Text(title, style = MaterialTheme.typography.titleLarge)
                            Text("Jun 2023 • 140 mins • PG")
                            Text("Action • Adventure • Sci-fi")
                        }
                    }
                }
            }
            stickyHeader {
                val backGroundColor = MaterialTheme.colorScheme.surface
                Box(
                    Modifier
                        .drawBehind {
                            drawRect(if (isCollapsed) backGroundColor else Color.Transparent)
                        }
                        .statusBarsPadding()
                        .height(64f.dp)
                        .fillMaxWidth()
//                        .background(if(isCollapsed) backGroundColor else Color.Transparent)

                )
            }
            items(20) {
                Box(
                    Modifier
                        .background(Color(0xFF000000 + Random.nextLong(0xFFFFFF)))
                        .height(64f.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieScreenPreview() {
    SnacTheme {
        MovieDetailsScreen()
    }
}