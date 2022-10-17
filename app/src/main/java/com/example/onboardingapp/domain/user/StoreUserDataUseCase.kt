package com.example.onboardingapp.domain.user

import com.example.onboardingapp.data.UserStorage
import com.example.onboardingapp.domain.model.User
import javax.inject.Inject

interface StoreUserDataUseCase {

    operator fun invoke(user: User)
}

class StoreUserDataUseCaseImpl @Inject constructor(
    private val userStorage: UserStorage
) : StoreUserDataUseCase {

    override fun invoke(user: User) {
        userStorage.store(user)
    }
}
