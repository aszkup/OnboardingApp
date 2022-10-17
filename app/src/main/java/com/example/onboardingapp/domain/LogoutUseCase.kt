package com.example.onboardingapp.domain

import com.example.onboardingapp.data.CredentialsStorage
import com.example.onboardingapp.data.UserStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LogoutUseCase {

    suspend operator fun invoke(): Flow<Unit>
}

class LogoutUseCaseImpl @Inject constructor(
    private val userStorage: UserStorage,
    private val credentialsStorage: CredentialsStorage,
) : LogoutUseCase {

    override suspend fun invoke(): Flow<Unit> = flow {
        userStorage.clear()
        credentialsStorage.clear()
        emit(Unit)
    }
}
