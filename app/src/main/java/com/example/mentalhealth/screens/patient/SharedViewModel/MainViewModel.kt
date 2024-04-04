package com.example.mentalhealth.screens.patient.SharedViewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.local.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appPref : AppPreferences): ViewModel() {
    val userType = appPref.getUser().stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(),
        initialValue = "")

    val isFirstStepComp = appPref.getIsFirstStepCompleted().stateIn(scope = viewModelScope, started = SharingStarted.Eagerly,
        initialValue = false)


    fun saveUserType(user : String){
        viewModelScope.launch {
            appPref.saveUser(user)
        }
    }

    fun saveFirstStepComp(comp : Boolean){
        viewModelScope.launch {
            appPref.setIsFirstStepCompleted(completed = comp)
        }
    }
}