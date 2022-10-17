package com.example.onboardingapp.ui.onboarding

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.onboardingapp.rememberParentEntry
import com.example.onboardingapp.ui.onboarding.credentials.CredentialScreen
import com.example.onboardingapp.ui.onboarding.credentials.NAV_CREDENTIALS
import com.example.onboardingapp.ui.onboarding.personal_Info.NAV_PERSONAL_INFO
import com.example.onboardingapp.ui.onboarding.personal_Info.PersonalInfoScreen
import com.example.onboardingapp.ui.onboarding.pin.NAV_CONFIRM_PIN
import com.example.onboardingapp.ui.onboarding.pin.NAV_PIN
import com.example.onboardingapp.ui.onboarding.pin.PinLaunchMode
import com.example.onboardingapp.ui.onboarding.pin.PinScreen
import com.example.onboardingapp.ui.onboarding.tos.NAV_TOS
import com.example.onboardingapp.ui.onboarding.tos.TermsOfServiceScreen
import com.example.onboardingapp.ui.onboarding.welcome.NAV_WELCOME
import com.example.onboardingapp.ui.onboarding.welcome.WelcomeScreen

const val ROUTE_ONBOARDING = "route_onboarding"

fun NavGraphBuilder.onboardingGraph(navController: NavController, exitRoute: String) {
    navigation(startDestination = NAV_WELCOME, route = ROUTE_ONBOARDING) {
        composable(NAV_WELCOME) {
            WelcomeScreen(
                onStartClicked = { navController.navigate(NAV_TOS) }
            )
        }
        composable(NAV_TOS) {
            TermsOfServiceScreen(
                onboardingViewModel = hiltViewModel(it.rememberParentEntry(navController)),
                onNextClicked = { navController.navigate(NAV_CREDENTIALS) }
            )
        }
        composable(NAV_CREDENTIALS) {
            CredentialScreen(
                onboardingViewModel = hiltViewModel(it.rememberParentEntry(navController)),
                onNextClicked = { navController.navigate(NAV_PERSONAL_INFO) }
            )
        }
        composable(NAV_PERSONAL_INFO) {
            PersonalInfoScreen(
                onboardingViewModel = hiltViewModel(it.rememberParentEntry(navController)),
                onNextClicked = { navController.navigate(NAV_PIN) }
            )
        }
        composable(NAV_PIN) {
            PinScreen(
                onboardingViewModel = hiltViewModel(it.rememberParentEntry(navController)),
                launchMode = PinLaunchMode.AddPin,
                onNextClicked = { navController.navigate(NAV_CONFIRM_PIN) }
            )
        }
        composable(NAV_CONFIRM_PIN) {
            PinScreen(
                onboardingViewModel = hiltViewModel(it.rememberParentEntry(navController)),
                launchMode = PinLaunchMode.ConfirmPin,
                onNextClicked = {
                    navController.navigate(route = exitRoute) {
                        popUpTo(route = exitRoute) { inclusive = true }
                    }
                }
            )
        }
    }
}
