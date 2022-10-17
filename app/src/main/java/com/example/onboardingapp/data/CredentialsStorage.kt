package com.example.onboardingapp.data

import com.example.onboardingapp.domain.model.Credentials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val USER_EMAIL = "user_email"
private const val USER_PASS = "user_password"

interface CredentialsStorage {

    fun store(credentials: Credentials)

    fun get(): Flow<Credentials?>

    fun clear()
}

class CredentialsStorageImpl @Inject constructor(
    private val appPreferences: AppPreferences
) : CredentialsStorage {

    override fun store(credentials: Credentials) {
        appPreferences.editor().apply {
            putString(USER_EMAIL, credentials.email)
            putString(USER_PASS, credentials.password)
        }.commit()
    }

    override fun get(): Flow<Credentials?> = flow {
        val prefs = appPreferences.preferences()
        if (prefs.contains(USER_EMAIL).not()) {
            emit(null)
            return@flow
        }
        val credentials = Credentials(
            email = prefs.getString(USER_EMAIL, "")!!,
            password = prefs.getString(USER_PASS, "")!!,
        )
        emit(credentials)
    }

    override fun clear() {
        appPreferences.editor().apply {
            remove(USER_EMAIL)
            remove(USER_PASS)
        }.commit()
    }
}
