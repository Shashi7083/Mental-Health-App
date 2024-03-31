package com.example.mentalhealth.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow


//create preferences key
val USER_KEY = stringPreferencesKey("user_type")
val FIRSTSTEP_KEY = booleanPreferencesKey("is_first_step_completed")
interface AppPreferences {
    fun getUser(): Flow<String>
    suspend fun saveUser(user : String)

    fun getIsFirstStepCompleted(): Flow<Boolean>

    suspend fun setIsFirstStepCompleted(completed : Boolean)
}