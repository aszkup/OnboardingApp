package com.example.onboardingapp.domain.di

import com.example.onboardingapp.domain.LogoutUseCase
import com.example.onboardingapp.domain.LogoutUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class SessionUseCasesModule {

    @Binds
    abstract fun bindLogoutUseCase(useCase: LogoutUseCaseImpl): LogoutUseCase
}
