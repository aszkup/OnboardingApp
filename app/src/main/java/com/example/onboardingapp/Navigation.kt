package com.example.onboardingapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.onboardingapp.ui.main.ROUTE_MAIN
import com.example.onboardingapp.ui.main.mainGraph
import com.example.onboardingapp.ui.onboarding.ROUTE_ONBOARDING
import com.example.onboardingapp.ui.onboarding.onboardingGraph
import com.example.onboardingapp.ui.splash.NAV_SPLASH
import com.example.onboardingapp.ui.splash.SplashScreen

const val ROOT_ROUTE = "root_route"

@Composable
fun RootGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NAV_SPLASH, route = ROOT_ROUTE) {
        composable(NAV_SPLASH) {
            SplashScreen(
                viewModel = hiltViewModel(),
                proceedToMainScreen = {
                    navController.navigate(ROUTE_MAIN) {
                        launchSingleTop = true
                        popUpTo(NAV_SPLASH) { inclusive = true }
                    }
                },
                proceedToOnboardingFlow = {
                    navController.navigate(ROUTE_ONBOARDING) {
                        launchSingleTop = true
                        popUpTo(NAV_SPLASH) { inclusive = true }
                    }
                }
            )
        }
        mainGraph(navController, exitRoute = ROOT_ROUTE)
        onboardingGraph(navController, exitRoute = ROOT_ROUTE)
    }
}

@Composable
fun NavBackStackEntry.rememberParentEntry(navController: NavController): NavBackStackEntry {
    val parentId = destination.parent!!.id

    return remember(this) {
        navController.getBackStackEntry(parentId)
    }
}
