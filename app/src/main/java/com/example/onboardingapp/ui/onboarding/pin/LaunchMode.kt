package com.example.onboardingapp.ui.onboarding.pin

sealed class PinLaunchMode {
    object AddPin : PinLaunchMode()
    object ConfirmPin : PinLaunchMode()
    object VerifyPin : PinLaunchMode()
}
