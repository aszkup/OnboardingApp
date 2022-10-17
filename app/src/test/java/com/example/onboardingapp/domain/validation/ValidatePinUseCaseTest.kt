package com.example.onboardingapp.domain.validation

import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ValidatePinUseCaseTest {

    private lateinit var sut: ValidatePinUseCase

    @Before
    fun setUp() {
        sut = ValidatePinUseCaseImpl()
    }

    @Test
    fun `GIVEN valid pin WHEN validate THEN return Valid result`() = runBlocking {
        val pin = "1234"

        val result = sut.invoke(pin)

        Assert.assertEquals(ValidationResult.Valid, result)
    }

    @Test
    fun `GIVEN empty pin WHEN validate THEN return Empty result`() = runBlocking {
        val pin = ""

        val result = sut.invoke(pin)

        Assert.assertEquals(ValidationResult.Empty, result)
    }
}