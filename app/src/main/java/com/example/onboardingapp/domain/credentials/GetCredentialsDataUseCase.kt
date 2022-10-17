package com.example.onboardingapp.domain.credentials

import com.example.onboardingapp.data.CredentialsStorage
import com.example.onboardingapp.domain.model.Credentials
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCredentialsDataUseCase {

    operator fun invoke(): Flow<Credentials?>
}

class GetCredentialsDataUseCaseImpl @Inject constructor(
    private val credentialsStorage: CredentialsStorage
) : GetCredentialsDataUseCase {

    override fun invoke(): Flow<Credentials?> {
        return credentialsStorage.get()
    }
}
