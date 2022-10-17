package com.example.onboardingapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import javax.inject.Inject

private const val SECRET_FILE_NAME = "secret_shared_prefs"

class AppPreferences @Inject constructor(
    context: Context
) {

    private var masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        SECRET_FILE_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun preferences() = sharedPreferences

    fun editor() = sharedPreferences.edit()
}
