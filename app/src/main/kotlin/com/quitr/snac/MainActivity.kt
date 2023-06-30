package com.quitr.snac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.quitr.snac.core.ui.theme.SnacTheme
import com.quitr.snac.navigation.SnacNavHost
import dagger.hilt.android.AndroidEntryPoint

//import androidx.lifecycle.viewmodel.compose.viewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            SnacTheme {
                val navController = rememberNavController()
                val navBarController = rememberNavController()
                SnacNavHost(navController = navController, navBarController = navBarController)

            }
        }
    }
}
