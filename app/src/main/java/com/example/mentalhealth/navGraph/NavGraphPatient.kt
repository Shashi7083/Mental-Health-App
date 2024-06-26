package com.example.mentalhealth.navGraph

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mentalhealth.sampleApi.ui.SampleViewModel
import com.example.mentalhealth.screens.patient.SharedViewModel.MainViewModel
import com.example.mentalhealth.screens.patient.doctor.MainScreenDoctor
import com.example.mentalhealth.screens.patient.patient.onBoarding.LoginScreen
import com.example.mentalhealth.screens.patient.patient.onBoarding.QuestionsScreen
import com.example.mentalhealth.screens.patient.StartScreen
import com.example.mentalhealth.screens.patient.patient.DoctorDetails
import com.example.mentalhealth.screens.patient.SharedViewModel.PatientViewModel
import com.example.mentalhealth.screens.patient.patient.bottomNav.AiChat
import com.example.mentalhealth.screens.patient.patient.bottomNav.DoctorsListScreen
import com.example.mentalhealth.screens.patient.patient.bottomNav.Home
import com.example.mentalhealth.screens.patient.patient.bottomNav.ResScreen
import com.example.mentalhealth.screens.patient.patient.screen.AiChatContentScreen
import com.example.mentalhealth.screens.patient.patient.screen.PatientProfileScreen

@Composable
fun NavGraphPatient(
    startDestination : String,
    sampleViewModel: SampleViewModel,
    mainViewModel: MainViewModel
){

    val viewModel : PatientViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination ){
       
       
        //onBoarding patient 
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.StartScreen.route
        ){
            composable(
                route = Route.StartScreen.route
            ){
                StartScreen(navController = navController)
            }

            composable(
                route= Route.PatientLoginScreen.route
            ){
                LoginScreen(navController = navController)
            }


        }

        // patient question screen
        navigation(
            route = Route.PatientQuestioningNavigation.route,
            startDestination = Route.QuestionsScreen.route
        ){
            composable(
                route = Route.QuestionsScreen.route
            ){
                QuestionsScreen(navController = navController , mainViewModel = mainViewModel)
            }
        }

        // Patient Bottom Navigations
        navigation(
            route = Route.PatientNavigation.route,
            startDestination = BottomNavScreen.HomeScreen.route
        ){


            composable(
                route = BottomNavScreen.HomeScreen.route
            ){
                Home(navController = navController)
            }

            composable(
                route = BottomNavScreen.ResScreen.route
            ){
                ResScreen(navController = navController,viewModel = sampleViewModel)
            }

            composable(
                route = BottomNavScreen.DoctorsListScreen.route
            ){
                DoctorsListScreen(navController = navController, viewModel = viewModel)
            }

            composable(
                route = Route.DoctorDetails.route
            ){
                DoctorDetails(navController = navController, viewModel = viewModel)
            }

            composable(
                route = Route.PatientProfileScreen.route
            ){
                PatientProfileScreen(navController = navController)
            }

            composable(
                route = Route.AiChatContentScreen.route
            ){
                AiChatContentScreen(navController = navController)
            }
        }
        
        

        // Doctor navigation
        navigation(
            route = Route.DoctorNavigation.route,
            startDestination = Route.MainScreenDoctor.route
        ){
            composable(
                route= Route.MainScreenDoctor.route
            ){
                MainScreenDoctor(navController = navController)
            }
        }
    }

}