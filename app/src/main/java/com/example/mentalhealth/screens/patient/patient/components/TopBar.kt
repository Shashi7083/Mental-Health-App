package com.example.mentalhealth.screens.patient.patient.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mentalhealth.navGraph.BottomNavScreen

@Composable
fun TopBar(
    navController : NavHostController
){

    val backStackEntry = navController.currentBackStackEntryAsState().value
    var stackScreen = backStackEntry?.destination?.route
    var selectedScreen = backStackEntry?.destination?.route

    TopAppBar (
        backgroundColor = Color.White,
      //  elevation = 2.dp,
        title = {
            Text(text = "Health Care")
        },
        actions = {

            selectedScreen?.let {
                if (it == BottomNavScreen.ResScreen.route || it == BottomNavScreen.DoctorsListScreen.route) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                    }
                }
            }

            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
            }

        }
    )

}