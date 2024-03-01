package com.example.mentalhealth.navGraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed  class BottomNavScreen(
    val route: String,
    val icon: ImageVector,
    val name: String
){
        object HomeScreen : BottomNavScreen(
            route = "home_screen",
            icon = Icons.Outlined.Home,
            name = "Home"
        )

        object  ResScreen : BottomNavScreen(
            route = "resource_screen",
            icon = Icons.Outlined.List,
            name = "Resources"
        )

        object DoctorsListScreen : BottomNavScreen(
            route = "doctor_list_screen",
            icon = Icons.Outlined.Face,
            name = "Doctors"
        )
}