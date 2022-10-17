package com.example.onboardingapp.domain.validation

import com.example.onboardingapp.R
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ValidateEmailUseCaseTest {

    private val mailPatternMatcher = mockk<MailPatternMatcher>(relaxed = true)

    private lateinit var sut: ValidateEmailUseCaseImpl

    @Before
    fun setUp() {
        sut = ValidateEmailUseCaseImpl(mailPatternMatcher)
    }

    @Test
    fun `GIVEN valid email WHEN validate THEN return Valid result`() = runBlocking {
        val email = "asd@gmail.com"

        every { mailPatternMatcher.match(any()) } returns true

        val result = sut.invoke(email)

        assertEquals(ValidationResult.Valid, result)
    }

    @Test
    fun `GIVEN invalid email WHEN validate THEN return Error result`() = runBlocking {
        val email = "@gmail.com"

        every { mailPatternMatcher.match(any()) } returns false

        val result = sut.invoke(email)

        assertEquals(ValidationResult.Error(R.string.invalid_email), result)
    }

    @Test
    fun `GIVEN invalid email WHEN validate THEN return Error result (2)`() = runBlocking {
        val email = "asd@.com"

        every { mailPatternMatcher.match(any()) } returns false

        val result = sut.invoke(email)

        assertEquals(ValidationResult.Error(R.string.invalid_email), result)
    }

    @Test
    fun `GIVEN invalid email WHEN validate THEN return Error result (3)`() = runBlocking {
        val email = "asd@asd"

        every { mailPatternMatcher.match(any()) } returns false

        val result = sut.invoke(email)

        assertEquals(ValidationResult.Error(R.string.invalid_email), result)
    }

    @Test
    fun `GIVEN invalid email WHEN validate THEN return Error result (4)`() = runBlocking {
        val email = "asd@asd."

        every { mailPatternMatcher.match(any()) } returns false

        val result = sut.invoke(email)

        assertEquals(ValidationResult.Error(R.string.invalid_email), result)
    }
}
