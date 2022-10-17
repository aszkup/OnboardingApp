package com.example.onboardingapp.domain.di

import com.example.onboardingapp.domain.credentials.GetCredentialsDataUseCase
import com.example.onboardingapp.domain.credentials.GetCredentialsDataUseCaseImpl
import com.example.onboardingapp.domain.credentials.StoreCredentialsUseCase
import com.example.onboardingapp.domain.credentials.StoreCredentialsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CredentialsUseCasesModule {

    @Binds
    abstract fun bindGetCredentialsUseCase(useCase: GetCredentialsDataUseCaseImpl): GetCredentialsDataUseCase

    @Binds
    abstract fun bindStoreUserUseCase(useCase: StoreCredentialsUseCaseImpl): StoreCredentialsUseCase
}
