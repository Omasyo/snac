package com.quitr.snac.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.model.Person
import com.quitr.snac.core.ui.card.PersonCard
import com.quitr.snac.core.ui.extensions.plus
import com.quitr.snac.core.ui.theme.SnacIcons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditsScreen(
    modifier: Modifier = Modifier,
    title: String,
    onPersonCardTap: (id: Int) -> Unit,
    onBackPressed: () -> Unit,
    people: List<Person>,
) {
    Scaffold(modifier, topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(SnacIcons.ArrowBack, null)
            }
        }, title = {
            Text(title)
        })
    }) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(96f.dp),
            contentPadding = innerPadding + PaddingValues(16f.dp),
            horizontalArrangement = Arrangement.spacedBy(8f.dp),
            verticalArrangement = Arrangement.spacedBy(16f.dp),
        ) {
            items(people, key = { person -> person.id }) { person ->
                PersonCard(
                    Modifier.aspectRatio(3f / 5f),
                    name = person.name,
                    photoUrl = person.photoUrl,
                    role = person.role,
                    onClick = {
                        onPersonCardTap(person.id)
                    })
            }
        }
    }
}