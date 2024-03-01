package com.example.mentalhealth.screens.patient

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealth.R
import com.example.mentalhealth.navGraph.Route

@Composable
fun StartScreen(
    navController : NavHostController
){

    val myfont = FontFamily(
        Font(R.font.dmsans_regular),
        Font(R.font.dmsans_semibold),
        Font(R.font.dmsans_bold),
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_mentalhealth),
                contentDescription ="background",
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                )

            Text(
                text = "Living 24 hours with \nmindfullness is more worthwhile\nthan living 100 years  without it \n-Buddha",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = myfont,
                    fontSize = 20.sp
                )

            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(50.dp),
                modifier = Modifier.padding(top = 30.dp)
            ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.clickable {  }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mental_health),
                            contentDescription = "mental_health",
                            modifier = Modifier.height(70.dp).width(70.dp).clickable {
                                navController.navigate(Route.PatientLoginScreen.route){
                                    popUpTo(Route.PatientLoginScreen.route){
                                        inclusive = true
                                    }

                                }
                            }
                        )

                        Text(
                            text = "Health Support",
                            style = TextStyle(
                                fontFamily = myfont,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.doctor),
                        contentDescription = "mental_health",
                        modifier = Modifier
                            .height(70.dp)
                            .border(width = 2.dp, color = androidx.compose.ui.graphics.Color.DarkGray)
                            .clickable {
                                navController.navigate(Route.MainScreenDoctor.route){
                                    popUpTo(Route.MainScreenDoctor.route){
                                        inclusive = true
                                    }

                                }
                            }
                    )

                    Text(
                        text = "Doctor",
                        style = TextStyle(
                            fontFamily = myfont,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }

}


