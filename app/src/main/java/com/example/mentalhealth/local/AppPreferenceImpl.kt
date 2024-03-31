package com.example.mentalhealth.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class AppPreferenceImpl(private val dataStore: DataStore<Preferences>) : AppPreferences {
    override fun getUser(): Flow<String> {
        return dataStore.data.catch { emit(emptyPreferences()) }.map {
            it[USER_KEY] ?:""
        }
    }

    override suspend fun saveUser(user: String) {
        dataStore.edit {
            it[USER_KEY] = user
        }
    }

    override fun getIsFirstStepCompleted(): Flow<Boolean> {
       return dataStore.data.catch { emit(emptyPreferences()) }.map {
           it[FIRSTSTEP_KEY] ?: false
       }
    }

    override suspend fun setIsFirstStepCompleted(completed: Boolean) {
        dataStore.edit {
        // here we can add this using api
        it[FIRSTSTEP_KEY] = completed
        }
    }
}