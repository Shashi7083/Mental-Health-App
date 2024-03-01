package com.example.mentalhealth.screens.patient.patient.bottomNav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mentalhealth.screens.patient.patient.components.BottomBarPatient
import com.example.mentalhealth.screens.patient.patient.components.TopBar

@Composable
fun ResScreen(
    navController: NavHostController
){
    Scaffold(
        topBar = {
                 TopBar(navController = navController)
        },
        bottomBar = {
            BottomBarPatient(navController = navController)
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it),
            contentAlignment = Alignment.Center){
            Text(text = "Resource Screen")
        }
    }
}