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
import com.quitr.snac.feature.home.HomeRoute
import com.quitr.snac.feature.home.SectionRoute

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
            HomeRoute(
                onSectionClicked = { sectionType ->
                    navController.navigate(SnacRoutes.section(sectionType))
                },
                onTvCardClicked = { },
                onMovieCardClicked = { },
            )
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
                onBackPressed = { navController.popBackStack() })
        }
    }
}