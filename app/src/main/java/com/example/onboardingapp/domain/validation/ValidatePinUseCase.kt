package com.example.onboardingapp.domain.validation

import javax.inject.Inject

interface ValidatePinUseCase {

    suspend operator fun invoke(pin: String): ValidationResult
}

class ValidatePinUseCaseImpl @Inject constructor() : ValidatePinUseCase {

    override suspend fun invoke(pin: String): ValidationResult {
        return when {
            pin.isEmpty() -> ValidationResult.Empty
            else -> ValidationResult.Valid
            // ValidationResult.Error when more rules will be applied
        }
    }
}