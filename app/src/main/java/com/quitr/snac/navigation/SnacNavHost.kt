package com.quitr.snac.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.quitr.snac.core.data.SectionType
import com.quitr.snac.feature.home.SectionRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SnacNavHost(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController = rememberAnimatedNavController(),
    navBarController: NavHostController = rememberAnimatedNavController(),
) {
    val innerNav = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = rootNavController,
        startDestination = SnacRoutes.root,
        modifier = modifier
    ) {
        composable(SnacRoutes.root) {
            RootRoute(rootNavController = rootNavController, navBarController = navBarController)

        }
        composable(
            SnacRoutes.section,
            arguments = listOf(navArgument("sectionType") {
                type = NavType.EnumType(SectionType::class.java)
            })
        ) { backStackEntry ->

            val sectionType = backStackEntry.arguments?.get("sectionType") as SectionType
            SectionRoute(
                sectionType = sectionType,
                onMovieCardTap = {},
                onTvCardTap = {},
                onBackPressed = { rootNavController.popBackStack() })
        }
    }
}