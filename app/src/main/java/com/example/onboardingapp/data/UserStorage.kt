package com.example.onboardingapp.data

import com.example.onboardingapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val USER_NAME = "user_name"
private const val USER_LAST_NAME = "user_last_name"
private const val USER_PHONE = "user_phone"

interface UserStorage {

    fun store(user: User)

    suspend fun get(): Flow<User?>

    fun clear()
}

class UserStorageImpl @Inject constructor(
    private val appPreferences: AppPreferences
) : UserStorage {

    override fun store(user: User) {
        appPreferences.editor().apply {
            putString(USER_NAME, user.firstName)
            putString(USER_LAST_NAME, user.lastName)
            putString(USER_PHONE, user.phone)
        }.commit()
    }

    override suspend fun get(): Flow<User?> = flow {
        val prefs = appPreferences.preferences()
        if (prefs.contains(USER_NAME).not()) {
            emit(null)
            return@flow
        }

        val user = User(
            firstName = prefs.getString(USER_NAME, "")!!,
            lastName = prefs.getString(USER_LAST_NAME, "")!!,
            phone = prefs.getString(USER_PHONE, "")!!,
        )
        emit(user)
    }

    override fun clear() {
        appPreferences.editor().apply {
            remove(USER_NAME)
            remove(USER_LAST_NAME)
            remove(USER_PHONE)
        }.commit()
    }
}
