package com.keetr.snac.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.ui.theme.SnacTheme


@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Surface(modifier) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(
                    Modifier
                        .background(MaterialTheme.colorScheme.onSurface.copy(0.2f))
                        .size(200f.dp)
                )
                Spacer(Modifier.height(16f.dp))
                Text("An error occured", style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(8f.dp))
                FilledTonalButton(onClick = onRetry) {
                    Text(text = "Retry", modifier = Modifier.padding(horizontal = 16f.dp))
                }
            }
    }
}


@Preview
@Composable
private fun Preview() {
    SnacTheme {
        ErrorScreen {

        }
    }
}