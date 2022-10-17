package com.example.onboardingapp.domain.di

import com.example.onboardingapp.domain.user.GetUserDataUseCase
import com.example.onboardingapp.domain.user.GetUserDataUseCaseImpl
import com.example.onboardingapp.domain.user.StoreUserDataUseCase
import com.example.onboardingapp.domain.user.StoreUserDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserUseCasesModule {

    @Binds
    abstract fun bindGetUserDataUseCase(useCase: GetUserDataUseCaseImpl): GetUserDataUseCase

    @Binds
    abstract fun bindStoreUserDataUseCase(useCase: StoreUserDataUseCaseImpl): StoreUserDataUseCase

}
