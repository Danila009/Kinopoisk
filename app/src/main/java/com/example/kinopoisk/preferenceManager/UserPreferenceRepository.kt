package com.example.kinopoisk.preferenceManager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceRepository @Inject constructor (
    @ApplicationContext private val context: Context
) {

    private val Context.userDataStore by preferencesDataStore("user_data_store")

    suspend fun saveStatusRegistration(userRegistration:Boolean){
        context.userDataStore.edit { preferences ->
            preferences[USER_STATUS_REGISTRATION] = userRegistration
        }
    }

    fun readStatusRegistration():Flow<Boolean>{
        return context.userDataStore.data
            .map { preferences ->
                preferences[USER_STATUS_REGISTRATION] ?: false
            }
    }

    companion object {
        val USER_STATUS_REGISTRATION = booleanPreferencesKey("user_status_registration_key")
    }
}