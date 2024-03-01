package com.example.mentalhealth.screens.patient.patient.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealth.R
import com.example.mentalhealth.navGraph.Route


@Composable
fun LoginScreen(
    navController: NavHostController
){

    var phone   by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.login_bg_patient),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentDescription = null ,
            contentScale = ContentScale.FillHeight)

        Column(
            modifier = Modifier.fillMaxWidth(0.76f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
                Image(painter = painterResource(id = R.drawable.group_people),
                    contentDescription = null,
                    modifier = Modifier)
            
            
            Text(
                text = "Happines can be found even in the darkest of times. ",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )


            TextField(
                value = phone,
                onValueChange = { newText->
                    if(newText.length <10){
                        phone = newText
                    }
                                },
                label = { 
                    Text(text = "Phone Number")
                },
                placeholder = {
                    Text(text = "62*******99")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Phone, contentDescription = null)
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number)
                )
            
            TextButton(
                onClick = {
                          navController.navigate(Route.QuestionsScreen.route){
                              popUpTo(Route.QuestionsScreen.route){
                                  inclusive = true
                              }
                          }
                },
                modifier = Modifier
                    .padding(4.dp)
                    .border(width = 2.dp, shape = RoundedCornerShape(8.dp), color = Color.Gray)
                    .background(Color.LightGray)) {
                Text(text = "Continue with this phone")
                Spacer(modifier = Modifier.width(15.dp))
                Image(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}