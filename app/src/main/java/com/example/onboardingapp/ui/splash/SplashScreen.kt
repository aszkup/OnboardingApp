package com.example.onboardingapp.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope

const val NAV_SPLASH = "nav_splash"

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    proceedToMainScreen: () -> Unit,
    proceedToOnboardingFlow: () -> Unit,
) {
    LaunchedEffect(rememberCoroutineScope()) {
        viewModel.checkUserState(proceedToMainScreen, proceedToOnboardingFlow)
    }
}
