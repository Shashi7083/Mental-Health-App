package com.example.mentalhealth.screens.patient.patient.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mentalhealth.navGraph.BottomNavScreen
import com.example.mentalhealth.ui.theme.drop

@Composable
fun BottomBarPatient(
    navController : NavHostController
){

    val bottomScreens = listOf(
        BottomNavScreen.HomeScreen,
        BottomNavScreen.ResScreen,
        BottomNavScreen.DoctorsListScreen
    )

    val backStackEntry = navController.currentBackStackEntryAsState().value
    var stackScreen = backStackEntry?.destination?.route
    var selectedScreen = backStackEntry?.destination?.route



    BottomNavigation(
        modifier = Modifier.height(70.dp),
        backgroundColor = Color.White,
        elevation = 15.dp
    ) {
        bottomScreens.forEachIndexed { index, screen ->
            BottomNavigationItem(
                selected = selectedScreen.equals(screen.route),
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(screen.route){
                            inclusive = true
                        }
                    }
                },
                icon = {

                    Box(
                        modifier = Modifier

                            .size(70.dp)
                           // .clip(CircleShape)
                            .padding(4.dp)

                            .shadow(
                                elevation = if(selectedScreen.equals(screen.route))5.dp else 0.dp,
                                shape = CircleShape,
                                //spotColor = Color.Red,
                                ambientColor = Color.Red,
                                clip = true
                            )
                            .background(
                                color = if(selectedScreen.equals(screen.route))drop else Color.White,
                                shape = CircleShape
                            )

                            ,
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = if(selectedScreen.equals(screen.route)){
                                Color.Blue.copy(alpha = 0.7f)
                            }else{
                                Color.Gray
                            }
                        )
                    }
                }
            )
        }
    }
}