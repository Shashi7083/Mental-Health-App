package com.example.mentalhealth.navGraph

sealed class Route (
    val route:String
){

    object StartScreen :Route(route = "start_screen")

    object PatientLoginScreen : Route(route = "patient_login_screen")

    object QuestionsScreen : Route(route = "question_screen")

    object AppStartNavigation: Route(route = "appStartNavigation")

    object PatientNavigation: Route(route = "patientNavigation")

    object PatientProfileScreen : Route(route = "patientProfileScreen")

    object AiChatContentScreen : Route(route = "AiChatContentScreen")
    object  DoctorNavigation : Route(route = "doctorNavigation")

    object MainScreenDoctor: Route(route = "mainScreenDoctor")

    object DoctorDetails : Route( route = "doctorDetails")
}