package com.example.onboardingapp.ui.onboarding

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingapp.R
import com.example.onboardingapp.domain.credentials.StoreCredentialsUseCase
import com.example.onboardingapp.domain.model.Credentials
import com.example.onboardingapp.domain.model.User
import com.example.onboardingapp.domain.user.StoreUserDataUseCase
import com.example.onboardingapp.domain.validation.ValidateEmailUseCase
import com.example.onboardingapp.domain.validation.ValidatePhoneUseCase
import com.example.onboardingapp.domain.validation.ValidatePinUseCase
import com.example.onboardingapp.domain.validation.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val VALIDATE_FIELD_DELAY = 200L

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val storeUserDataUseCase: StoreUserDataUseCase,
    private val storeCredentialsUseCase: StoreCredentialsUseCase,
    private val validatePinUseCase: ValidatePinUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePhoneUseCase: ValidatePhoneUseCase,
) : ViewModel() {

    val areTermsAccepted = mutableStateOf(false)

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")

    val phone = mutableStateOf("")
    val isPhoneValid = mutableStateOf(false)
    val isPhoneError = mutableStateOf(false)
    val phoneErrorText = mutableStateOf(R.string.empty)

    val email = mutableStateOf("")
    val isEmailValid = mutableStateOf(false)
    val isEmailError = mutableStateOf(false)
    val emailErrorText = mutableStateOf(R.string.empty)

    val password = mutableStateOf("")

    val pin = mutableStateOf("")
    val isPinValid = mutableStateOf(false)

    private var observeEmailJob: Job? = null
    private var observePinJob: Job? = null
    private var observePhoneJob: Job? = null

    fun storeData() {
        val user = User(
            firstName = firstName.value,
            lastName = lastName.value,
            phone = phone.value,
        )
        val credentials = Credentials(
            email = email.value,
            password = password.value,
        )
        viewModelScope.launch(Dispatchers.IO) {
            storeUserDataUseCase.invoke(user)
            storeCredentialsUseCase.invoke(credentials)
        }
    }

    fun observeEmail() {
        observeEmailJob?.cancel()
        @OptIn(FlowPreview::class)
        observeEmailJob = snapshotFlow { email.value }
            .debounce(VALIDATE_FIELD_DELAY)
            .onEach { validateEmail(it) }
            .launchIn(viewModelScope)
    }

    private fun validateEmail(email: String) {
        viewModelScope.launch {
            Timber.d("Validate email")
            when (val result = validateEmailUseCase(email)) {
                ValidationResult.Empty -> {
                    emailErrorText.value = R.string.empty
                    isEmailValid.value = false
                    isEmailError.value = false
                }
                is ValidationResult.Error -> {
                    emailErrorText.value = result.errorMessage
                    isEmailValid.value = false
                    isEmailError.value = true
                }
                ValidationResult.Valid -> {
                    emailErrorText.value = R.string.empty
                    isEmailValid.value = true
                    isEmailError.value = false
                }
            }
        }
    }

    fun observePin() {
        observePinJob?.cancel()
        @OptIn(FlowPreview::class)
        observePinJob = snapshotFlow { pin.value }
            .debounce(VALIDATE_FIELD_DELAY)
            .onEach { validatePin(it) }
            .launchIn(viewModelScope)
    }

    private fun validatePin(pin: String) {
        Timber.d("Validate pin")
        viewModelScope.launch {
            when (validatePinUseCase(pin)) {
                ValidationResult.Empty -> {
                    isPinValid.value = false
                }
                ValidationResult.Valid -> {
                    isPinValid.value = true
                }
                is ValidationResult.Error -> TODO() // Implement in case of adding Pin rules
            }
        }
    }

    fun observePhone() {
        observePhoneJob?.cancel()
        @OptIn(FlowPreview::class)
        observePhoneJob = snapshotFlow { phone.value }
            .debounce(VALIDATE_FIELD_DELAY)
            .onEach { validatePhone(it) }
            .launchIn(viewModelScope)
    }

    private fun validatePhone(phone: String) {
        Timber.d("Validate phone")
        viewModelScope.launch {
            when (val result = validatePhoneUseCase(phone)) {
                ValidationResult.Empty -> {
                    phoneErrorText.value = R.string.empty
                    isPhoneValid.value = false
                    isPhoneError.value = false
                }
                is ValidationResult.Error -> {
                    phoneErrorText.value = result.errorMessage
                    isPhoneValid.value = false
                    isPhoneError.value = true
                }
                ValidationResult.Valid -> {
                    phoneErrorText.value = R.string.empty
                    isPhoneValid.value = true
                    isPhoneError.value = false
                }
            }
        }
    }
}
