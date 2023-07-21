package com.keetr.snac.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keetr.snac.core.ui.theme.SnacIcons
import com.keetr.snac.core.ui.theme.SnacTheme
import com.keetr.snac.core.common.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(SnacIcons.ArrowBack, null)
                    }
                })
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(
                Modifier
                    .background(MaterialTheme.colorScheme.onSurface.copy(0.2f))
                    .size(200f.dp)
            )
            Spacer(Modifier.height(16f.dp))
            Text(stringResource(R.string.error_occurred), style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8f.dp))
            FilledTonalButton(onClick = onRetry) {
                Text(stringResource(R.string.retry), modifier = Modifier.padding(horizontal = 16f.dp))
            }
            Spacer(Modifier.height(64f.dp))
        }
    }
}


@Preview
@Composable
private fun Preview() {
    SnacTheme {
        ErrorScreen(Modifier, {}, {})
    }
}