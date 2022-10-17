package com.example.onboardingapp.domain.validation

import android.util.Patterns
import com.example.onboardingapp.R
import javax.inject.Inject

interface ValidateEmailUseCase {

    suspend operator fun invoke(email: String): ValidationResult
}

class ValidateEmailUseCaseImpl @Inject constructor(
    private val mailPatternMatcher: MailPatternMatcher
) : ValidateEmailUseCase {

    override suspend fun invoke(email: String): ValidationResult {
        return when {
            email.isEmpty() -> ValidationResult.Empty
            mailPatternMatcher.match(email) -> ValidationResult.Valid
            else -> ValidationResult.Error(R.string.invalid_email)
        }
    }
}

class MailPatternMatcher @Inject constructor() {

    private val regex = Regex(Patterns.EMAIL_ADDRESS.pattern())

    fun match(email: String) = email.matches(regex)
}
