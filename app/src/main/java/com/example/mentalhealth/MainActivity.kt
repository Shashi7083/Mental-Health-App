package com.example.mentalhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mentalhealth.local.Constants
import com.example.mentalhealth.local.Constants.DOCTOR
import com.example.mentalhealth.local.Constants.PATIENT
import com.example.mentalhealth.navGraph.NavGraphPatient
import com.example.mentalhealth.navGraph.Route
import com.example.mentalhealth.sampleApi.ui.SampleViewModel
import com.example.mentalhealth.screens.patient.SharedViewModel.MainViewModel
import com.example.mentalhealth.ui.theme.MentalHealthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel : SampleViewModel by viewModels()
    val mainViewModel : MainViewModel by viewModels()
    var startDestination : String = Route.AppStartNavigation.route

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MentalHealthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    mainViewModel.saveUserType(Constants.PATIENT)
                    mainViewModel.saveFirstStepComp(false)

                    val userType = mainViewModel.userType.collectAsState()
                    val isFirstStepComp  = mainViewModel.isFirstStepComp.collectAsState()

                    if(userType.value.equals("")){

                        startDestination = Route.AppStartNavigation.route

                    }else if(userType.value.equals(PATIENT)){

                        if(isFirstStepComp.value ){
                            startDestination = Route.PatientNavigation.route
                        }else{
                            startDestination = Route.PatientQuestioningNavigation.route
                        }
                    }else if(userType.value.equals(DOCTOR)){

                        if(isFirstStepComp.value){
                            startDestination = Route.DoctorNavigation.route
                        }else{
                            startDestination = Route.DoctorNavigation.route
                        }
                    }

//                    var working_on_home = false;
//                    var startDestination :String = Route.AppStartNavigation.route
//
//                    if(working_on_home){
//                        startDestination = Route.PatientNavigation.route
//                    }else{
//                        startDestination = Route.AppStartNavigation.route
//                    }
                    NavGraphPatient(
                        startDestination = startDestination,
                        sampleViewModel = viewModel,
                        mainViewModel = mainViewModel)
                }
            }
        }
    }
}

