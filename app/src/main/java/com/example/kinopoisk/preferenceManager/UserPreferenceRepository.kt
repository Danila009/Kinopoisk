package com.example.kinopoisk.preferenceManager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.kinopoisk.utils.UserRole
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceRepository @Inject constructor (
    @ApplicationContext private val context: Context
) {
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

    suspend fun savaUserRole(userRole:String){
        context.userDataStore.edit { preferences ->
            preferences[USER_ROLE] = userRole
        }
    }

    fun readUserRole():Flow<String>{
        return context.userDataStore.data
            .map { preferences ->
                preferences[USER_ROLE] ?: UserRole.BaseUser.name
            }
    }

    companion object {
        private val Context.userDataStore by preferencesDataStore(name = "user_data_store")
        val USER_STATUS_REGISTRATION = booleanPreferencesKey("user_status_registration_key")
        val USER_ROLE = stringPreferencesKey("user_role_key")
    }
}