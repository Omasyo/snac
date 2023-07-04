package com.quitr.snac.feature.people

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.model.Credit
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.ui.card.CreditCard
import com.quitr.snac.core.ui.carousel.Carousel

@Composable
fun CreditCarousel(
    modifier: Modifier = Modifier,
    category: String,
    credits: List<Credit>,
    onExpand: () -> Unit,
    onShowClicked: (showType: ShowType, showId: Int) -> Unit,
) {
    Carousel(
        modifier,
        header = {
            Text(category, style = MaterialTheme.typography.titleMedium)
        },
        onExpand = onExpand
    ) {
        items(
            credits,
            key = { credit -> credit.id },
        ) { credit ->
            with(credit) {
                CreditCard(
                    Modifier.size(120f.dp, 200f.dp),
                    title = title,
                    role = role,
                    rating = rating,
                    posterUrl = posterUrl,
                    onClick = { onShowClicked(showType, showId) }
                )
            }
        }
    }
}