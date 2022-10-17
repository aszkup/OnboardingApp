package com.example.onboardingapp.domain.validation

import com.example.onboardingapp.R
import com.google.i18n.phonenumbers.PhoneNumberUtil
import timber.log.Timber
import javax.inject.Inject

interface ValidatePhoneUseCase {

    suspend operator fun invoke(phone: String): ValidationResult
}

class ValidatePhoneUseCaseImpl @Inject constructor() : ValidatePhoneUseCase {

    private val phoneUtils = PhoneNumberUtil.getInstance()

    override suspend fun invoke(phone: String): ValidationResult {
        Timber.d("Validate phone: $phone")
        if (phone.isEmpty()) return ValidationResult.Empty
        try {
            // More complicated rules are welcome
            phoneUtils.parse(phone, "ZZ")
            return ValidationResult.Valid
        } catch (exception: Exception) {
            Timber.w(exception)
            return ValidationResult.Error(R.string.invalid_phone)
        }
    }
}
