package com.quitr.snac.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.quitr.snac.feature.home.HomeRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),


    ) {
    AnimatedNavHost(
        navController = navController,
        startDestination = SnacRoutes.home,
        modifier = modifier
    ) {
        composable(SnacRoutes.home) {
            HomeRoute()
        }
    }
}