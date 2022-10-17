package com.example.onboardingapp.testUtils

import org.junit.Rule

open class BaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
}
