package com.quitr.snac.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quitr.snac.core.data.Show
import com.quitr.snac.core.data.ShowType
import com.quitr.snac.core.ui.theme.SnacTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface(modifier) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8f.dp)) {
            items(10) {
                Section(name = "Section", type = ShowType.Movie, shows = shows, onExpand = {})
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    SnacTheme {
        HomeScreen()
    }
}

private val shows = List(4) {
    Show(
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg"
    )
}