package com.example.onboardingapp.domain.validation

import com.example.onboardingapp.R
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ValidatePhoneUseCaseTest {

    private lateinit var sut: ValidatePhoneUseCase

    @Before
    fun setUp() {
        sut = ValidatePhoneUseCaseImpl()
    }

    @Test
    fun `GIVEN valid Phone WHEN validate THEN return Valid result`() = runBlocking {
        val phone = "+49 744 55434"

        val result = sut.invoke(phone)

        Assert.assertEquals(ValidationResult.Valid, result)
    }

    @Test
    fun `GIVEN invalid Phone WHEN validate THEN return Error result`() = runBlocking {
        val phone = "+4"

        val result = sut.invoke(phone)

        Assert.assertEquals(ValidationResult.Error(R.string.invalid_phone), result)
    }

    @Test
    fun `GIVEN invalid Phone WHEN validate THEN return Error result (2)`() = runBlocking {
        val phone = "+49"

        val result = sut.invoke(phone)

        Assert.assertEquals(ValidationResult.Error(R.string.invalid_phone), result)
    }

    @Test
    fun `GIVEN invalid Phone WHEN validate THEN return Error result (3)`() = runBlocking {
        val phone = "4974455434"

        val result = sut.invoke(phone)

        Assert.assertEquals(ValidationResult.Error(R.string.invalid_phone), result)
    }

    @Test
    fun `GIVEN empty Phone WHEN validate THEN return Empty result`() = runBlocking {
        val phone = ""

        val result = sut.invoke(phone)

        Assert.assertEquals(ValidationResult.Empty, result)
    }
}