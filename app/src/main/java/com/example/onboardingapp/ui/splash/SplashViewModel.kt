package com.example.onboardingapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingapp.domain.user.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    fun checkUserState(onUserRegistered: () -> Unit, onUserNotRegistered: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            getUserDataUseCase()
                .catch { Timber.w(it) }
                .flowOn(Dispatchers.IO)
                .collect {
                    if (it != null) {
                        onUserRegistered()
                    } else {
                        onUserNotRegistered()
                    }
                }
        }
    }
}
