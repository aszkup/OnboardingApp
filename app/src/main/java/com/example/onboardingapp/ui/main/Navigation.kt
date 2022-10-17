package com.example.onboardingapp.ui.main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

const val NAV_MAIN = "nav_main"
const val ROUTE_MAIN = "route_main"

fun NavGraphBuilder.mainGraph(navController: NavHostController, exitRoute: String) {
    navigation(startDestination = NAV_MAIN, route = ROUTE_MAIN) {
        composable(route = NAV_MAIN) {
            MainScreen(
                mainViewModel = hiltViewModel(),
                onLogoutClicked = {
                    navController.navigate(route = exitRoute) {
                        launchSingleTop = true
                        popUpTo(exitRoute) { inclusive = true }
                    }
                }
            )
        }
    }
}
