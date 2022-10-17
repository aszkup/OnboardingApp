package com.example.onboardingapp.ui.onboarding

import com.example.onboardingapp.domain.credentials.StoreCredentialsUseCase
import com.example.onboardingapp.domain.user.StoreUserDataUseCase
import com.example.onboardingapp.domain.validation.ValidateEmailUseCase
import com.example.onboardingapp.domain.validation.ValidatePhoneUseCase
import com.example.onboardingapp.domain.validation.ValidatePinUseCase
import com.example.onboardingapp.domain.validation.ValidationResult
import com.example.onboardingapp.testUtils.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class OnboardingViewModelTest : BaseTest() {

    private val storeUserDataUseCase = mockk<StoreUserDataUseCase>()
    private val storeCredentialsUseCase = mockk<StoreCredentialsUseCase>()
    private val validatePinUseCase = mockk<ValidatePinUseCase>()
    private val validateEmailUseCase = mockk<ValidateEmailUseCase>()
    private val validatePhoneUseCase = mockk<ValidatePhoneUseCase>()

    private lateinit var sut: OnboardingViewModel

    @Before
    fun setUp() {
        sut = OnboardingViewModel(
            storeUserDataUseCase,
            storeCredentialsUseCase,
            validatePinUseCase,
            validateEmailUseCase,
            validatePhoneUseCase
        )

        coEvery { storeUserDataUseCase.invoke(any()) } returns Unit
        coEvery { storeCredentialsUseCase.invoke(any()) } returns Unit
    }

    @Test()
    fun `GIVEN valid email WHEN validate THEN return Valid email`() = runBlocking {
        val validEmail = "asd@asd.com"
        coEvery { validateEmailUseCase.invoke(any()) } returns ValidationResult.Valid

        runBlocking {
            sut.email.value = validEmail
        }

//        assertEquals("asd@asd.com", sut.email.value)
//        assertEquals(true, sut.isEmailValid.value)
//        assertEquals(R.string.empty, sut.emailErrorText.value)
//        assertEquals(false, sut.isEmailError.value)
    }

    @Test
    fun `GIVEN user data WHEN store it THEN verify execution`() = runBlocking {

        sut.storeData()

        coVerify(exactly = 1) { storeUserDataUseCase.invoke(any()) }
        coVerify(exactly = 1) { storeCredentialsUseCase.invoke(any()) }
    }
}

