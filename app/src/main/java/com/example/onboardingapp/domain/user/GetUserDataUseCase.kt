package com.example.onboardingapp.domain.user

import com.example.onboardingapp.data.UserStorage
import com.example.onboardingapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetUserDataUseCase {

    suspend operator fun invoke(): Flow<User?>
}

class GetUserDataUseCaseImpl @Inject constructor(
    private val userStorage: UserStorage
) : GetUserDataUseCase {

    override suspend fun invoke(): Flow<User?> {
        return userStorage.get()
    }
}
