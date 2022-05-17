package com.example.kinopoisk.preferenceManager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.core_database_domain.common.UserRole
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceRepository @Inject constructor (
    private val context: Context
) {

    fun readUserRole():Flow<String>{
        return context.userDataStore.data
            .map { preferences ->
                preferences[USER_ROLE] ?: UserRole.BaseUser.name
            }
    }

    companion object {
        private val Context.userDataStore by preferencesDataStore(name = "user_data_store")
        val USER_ROLE = stringPreferencesKey("user_role_key")
    }
}