package com.example.mentalhealth.screens.patient.patient.SharedViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mentalhealth.screens.patient.patient.bottomNav.DoctorModel

class PatientViewModel:ViewModel() {

    private val _docDetail: MutableState<DoctorModel> = mutableStateOf(DoctorModel("","","",0,"",""))
    val doc : State<DoctorModel> = _docDetail

    fun setData( data : DoctorModel){
        _docDetail.value = data
    }
}