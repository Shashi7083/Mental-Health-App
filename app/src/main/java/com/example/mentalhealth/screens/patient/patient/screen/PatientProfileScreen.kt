package com.example.mentalhealth.screens.patient.patient.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PatientProfileScreen(
    navController : NavHostController
){

    Box(
        modifier = Modifier.padding(start = 20.dp)
            .background(Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(50.dp))
            .clickable {
                navController.popBackStack()
            }){
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
    }

    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)

    ){

        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),

        ) {
            item {
                UserProfile()
            }

            item {
                Spacer(modifier = Modifier.height(18.dp))
                SessionCard()
            }

            item {
                Spacer(modifier = Modifier.height(15.dp))
                SettingItem(hicon = Icons.Default.AccountBox, name = "Preferences", ticon = Icons.Default.KeyboardArrowRight)
            }

            item {
                Spacer(modifier = Modifier.height(15.dp))
                SettingItem(hicon = Icons.Default.AccountBox, name = "Preferences", ticon = Icons.Default.KeyboardArrowRight)
            }

            item {
                Spacer(modifier = Modifier.height(15.dp))
                SettingItem(hicon = Icons.Default.AccountBox, name = "Preferences", ticon = Icons.Default.KeyboardArrowRight)
            }

            item {
                Spacer(modifier = Modifier.height(15.dp))
                SettingItem(hicon = Icons.Default.AccountBox, name = "Preferences", ticon = Icons.Default.KeyboardArrowRight)
            }

        }


    }
}


@Composable
fun UserProfile(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val brush = SolidColor(Color.Black.copy(alpha = 0.2f))
        Box (
            modifier = Modifier
                .fillMaxHeight(0.63f)
                .fillMaxWidth(0.4f)
                .border(width = 2.dp, brush = brush, shape = RoundedCornerShape(45.dp))
                .padding(20.dp)
                .background(Color.Gray.copy(alpha = 0.4f), shape = RoundedCornerShape(30.dp))

        ){

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Shashi Ranjan Kumar",
             fontSize = 25.sp,
            fontWeight = FontWeight.Bold
            )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "29% recovery per month" , fontSize = 15.sp, color = Color.Gray.copy(alpha = 0.9f))
    }

}

@Composable
fun SessionCard(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 20.dp, end = 20.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            Text(text = "Your Sessions")
            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "0", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Session")
                    Text(text = "Completed")
                }
                Box(modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .width(2.dp)
                    .background(Color.Gray))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "0", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Session")
                    Text(text = "Scheduled")
                }
                Box(modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .width(2.dp)
                    .background(Color.Gray))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "0", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Session")
                    Text(text = "Canceled")
                }

            }
            Spacer(modifier = Modifier.height(18.dp))

            Row (
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(0.5f)
                    .padding(end = 4.dp)
                    .background(Color(0xff08006F), shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "History",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold)
                }

                Box(modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(1f)
                    .padding(start = 4.dp)
                    .border(
                        width = 2.dp,
                        brush = SolidColor(Color(0xff08006F)),
                        shape = RoundedCornerShape(8.dp)
                    ),
                    contentAlignment = Alignment.Center
                    ){
                    Text(
                        text = "Scheduled",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun SettingItem(
    hicon : ImageVector,
    name : String,
    ticon : ImageVector
){
 Row(
     modifier = Modifier
         .fillMaxWidth()
         .height(60.dp)
         .padding(start = 20.dp, end = 20.dp)
         .shadow(elevation = 3.dp, shape = RoundedCornerShape(15.dp))
         .background(Color.White, shape = RoundedCornerShape(15.dp)),
     verticalAlignment = Alignment.CenterVertically
 ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.2f)
                .padding(7.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(15.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Icon(imageVector = hicon, contentDescription = name)
        }

     Spacer(modifier = Modifier.width(30.dp))
     Text(text = name, fontSize = 18.sp)
     Spacer(modifier = Modifier.fillMaxWidth(0.8f))
     Icon(imageVector = ticon, contentDescription = null)
 }
}

