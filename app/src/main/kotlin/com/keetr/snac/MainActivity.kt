package com.keetr.snac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.keetr.snac.core.ui.theme.SnacTheme
import com.keetr.snac.navigation.SnacNavHost
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
