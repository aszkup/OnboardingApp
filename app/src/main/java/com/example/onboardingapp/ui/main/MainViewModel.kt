package com.example.onboardingapp.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingapp.domain.LogoutUseCase
import com.example.onboardingapp.domain.user.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val phone = mutableStateOf("")

    fun loadUserData() {
        Timber.d("Load user data")
        viewModelScope.launch(Dispatchers.IO) {
            getUserDataUseCase()
                .catch { Timber.w(it) }
                .collect { user ->
                    user?.let {
                        firstName.value = user.firstName
                        lastName.value = user.lastName
                        phone.value = user.phone
                    }
                }
        }
    }

    fun onLogoutClicked(onFinished: () -> Unit) {
        Timber.i("On logout clicked")
        viewModelScope.launch(Dispatchers.Main) {
            logoutUseCase()
                .catch {
                    Timber.w(it)
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    onFinished.invoke()
                }
        }

    }
}
