package com.keetr.snac.core.ui.carousel

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.model.Person
import com.keetr.snac.core.ui.card.PersonCard

@Composable
fun PersonCarousel(
    modifier: Modifier = Modifier,
    category: String,
    people: List<Person>,
    onExpand: () -> Unit,
    onPersonClicked: (Int) -> Unit,
) {
    Carousel(
        modifier,
        header = {
            Text(category, style = MaterialTheme.typography.titleMedium)
        },
        onExpand = onExpand
    ) {
        items(
            people,
            key = { person -> person.id },
        ) { person ->
            PersonCard(
                Modifier.size(120f.dp, 200f.dp),
                name = person.name,
                role = person.role,
                photoUrl = person.photoUrl,
                onClick = { onPersonClicked(person.id) }
            )
        }
    }
}