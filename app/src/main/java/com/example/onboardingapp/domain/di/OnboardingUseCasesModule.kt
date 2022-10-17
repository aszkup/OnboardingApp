package com.example.onboardingapp.domain.di

import com.example.onboardingapp.domain.validation.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingUseCasesModule {

    @Binds
    abstract fun bindValidatePinUseCase(useCase: ValidatePinUseCaseImpl): ValidatePinUseCase

    @Binds
    abstract fun bindValidatePhoneUseCase(useCase: ValidatePhoneUseCaseImpl): ValidatePhoneUseCase

    @Binds
    abstract fun bindValidateEmailUseCase(useCase: ValidateEmailUseCaseImpl): ValidateEmailUseCase
}
