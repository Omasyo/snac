package com.quitr.snac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.navigation.SnacNavHost
import dagger.hilt.android.AndroidEntryPoint

//import com.quitr.snac.core.SnacTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            SnacTheme {
                Scaffold(
                    bottomBar = {
                        var selected by remember {
                            mutableStateOf(0)
                        }
                        NavigationBar() {
                            repeat(3) {
                                NavigationBarItem(
                                    selected = it == selected,
                                    onClick = { selected = it },
                                    icon = { Icon(Icons.Outlined.Home, null) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    SnacNavHost(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SnacTheme {
        Greeting("Android")
    }
}