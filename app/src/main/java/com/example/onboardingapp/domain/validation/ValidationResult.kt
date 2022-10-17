package com.example.onboardingapp.domain.validation

import androidx.annotation.StringRes

sealed class ValidationResult {
    object Valid : ValidationResult()
    object Empty : ValidationResult()
    data class Error(@StringRes val errorMessage: Int) : ValidationResult()
}
