package com.quitr.snac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.navigation.SnacNavHost
import dagger.hilt.android.AndroidEntryPoint

//import com.quitr.snac.core.SnacTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            SnacTheme {
                val navController = rememberAnimatedNavController()
                val navBarController = rememberAnimatedNavController()
                SnacNavHost(rootNavController = navController, navBarController = navBarController)

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