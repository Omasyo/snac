package com.quitr.snac.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.ui.theme.SnacTheme
import kotlin.random.Random

@Composable
fun SnacSearchBar() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchBarPreview() {
    SnacTheme {
        Scaffold(
            topBar = {
                var query by remember { mutableStateOf("") }
                var active by remember { mutableStateOf(false) }
                SearchBar(
//                    modifier = Modifier.fillMaxWidth(),
                    query = query,
                    onQueryChange = { query = it},
                    onSearch = { },
                    active = active,
                    onActiveChange = { active = it }
                ) {
                    repeat(20) {
                        Box(
                            Modifier
                                .background( Color(0xFF000000 + Random.nextLong(0xFFFFFF)) )
                                .fillMaxSize())
                    }
                }
            }
        ) {
            Box(
                Modifier
                    .padding(it)
                    .background(Color.Blue)
                    .fillMaxSize())
        }
    }
}