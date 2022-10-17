package com.example.onboardingapp.domain.credentials

import com.example.onboardingapp.data.CredentialsStorage
import com.example.onboardingapp.domain.model.Credentials
import javax.inject.Inject

interface StoreCredentialsUseCase {

    operator fun invoke(credentials: Credentials)
}

class StoreCredentialsUseCaseImpl @Inject constructor(
    private val credentialsStorage: CredentialsStorage
) : StoreCredentialsUseCase {

    override fun invoke(credentials: Credentials) {
        credentialsStorage.store(credentials)
    }
}
