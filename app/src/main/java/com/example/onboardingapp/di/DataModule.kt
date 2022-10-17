package com.example.onboardingapp.di

import com.example.onboardingapp.data.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCredentialsStorage(storageImpl: CredentialsStorageImpl): CredentialsStorage

    @Binds
    abstract fun bindUserStorage(storageImpl: UserStorageImpl): UserStorage
}
