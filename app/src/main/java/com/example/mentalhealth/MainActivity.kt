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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mentalhealth.navGraph.NavGraphPatient
import com.example.mentalhealth.navGraph.Route
import com.example.mentalhealth.sampleApi.ui.SampleViewModel
import com.example.mentalhealth.ui.theme.MentalHealthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel : SampleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MentalHealthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var working_on_home = true;
                    var startDestination :String = Route.AppStartNavigation.route

                    if(working_on_home){
                        startDestination = Route.PatientNavigation.route
                    }else{
                        startDestination = Route.AppStartNavigation.route
                    }
                    NavGraphPatient(
                        startDestination = startDestination,
                        sampleViewModel = viewModel)
                }
            }
        }
    }
}

