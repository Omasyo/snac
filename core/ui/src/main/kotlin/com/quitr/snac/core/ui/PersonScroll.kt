package com.quitr.snac.core.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.model.Person
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.section.ExpandableSection
import com.quitr.snac.core.ui.section.TypeContainer

@Composable
fun PersonScroll(
    modifier: Modifier = Modifier,
    category: String,
    people: List<Person>,
    onExpand: () -> Unit,
    onPersonClicked: (Int) -> Unit,
) {
    ExpandableSection(
        modifier,
        header = {
            Text(category, style = MaterialTheme.typography.titleLarge)
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