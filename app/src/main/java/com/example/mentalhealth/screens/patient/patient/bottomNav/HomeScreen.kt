package com.example.mentalhealth.screens.patient.patient.bottomNav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mentalhealth.R
import com.example.mentalhealth.navGraph.Route
import com.example.mentalhealth.screens.patient.patient.components.Articles
import com.example.mentalhealth.screens.patient.patient.components.BottomBarPatient
import com.example.mentalhealth.screens.patient.patient.components.Meditation
import com.example.mentalhealth.screens.patient.patient.components.MoodChart
import com.example.mentalhealth.screens.patient.patient.components.TopBar


@Composable
fun Home(
    navController : NavHostController
){

    val myfont = FontFamily(
        Font(R.font.dmsans_regular),
        Font(R.font.dmsans_semibold),
        Font(R.font.dmsans_bold),
    )

    var shown by remember {
        mutableStateOf(true) // set it to false and uncomment the commented for animation
    }


    Scaffold(
        topBar = {
                 TopBar(navController = navController)
        },
        bottomBar = {
            BottomBarPatient(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate(Route.AiChatContentScreen.route){
                              popUpTo(Route.AiChatContentScreen.route){
                                  inclusive = true
                              }
                          }
                },
                modifier = Modifier.clip(CircleShape)
            ) {
                AiChat()
            }
        }
    ) {

//        LaunchedEffect(Unit ){
//            shown = true
//        }
//
//        DisposableEffect(Unit){
//            onDispose {
//                shown = false
//            }
//        }

        AnimatedVisibility(
            visible = shown,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Column (
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    // .background(Color(0xfffafafa))
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                    .verticalScroll(rememberScrollState())
                ,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Box(modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth()
                    .background(
                        Color(0xff4755f5).copy(alpha = 0.6f),
                        shape = RoundedCornerShape(10.dp)
                    ),
                ){

                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(start = 10.dp, end = 10.dp, bottom = 8.dp, top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Complete your daily task" ,
                                color = Color.White,
                                style = TextStyle(
                                    fontFamily = myfont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(modifier = Modifier
                                .height(25.dp)
                                .background(Color.White, RoundedCornerShape(5.dp))
                                .padding(2.dp)){
                                Text(text = "5 min" , fontWeight = FontWeight.Bold)
                            }
                        }

                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null ,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            tint = Color.White
                        )
                    }
                }

                MoodChart()

                Meditation()

                Articles()

            }
        }



    }

}


@Composable
fun AiChat() {
    var isPlaying by remember {
        mutableStateOf(true)
    }

    var speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(

        LottieCompositionSpec
            // here `code` is the file name of lottie file
            // use it accordingly
            .RawRes(R.raw.ai_anime)
    )

    // to control the animation
    val progress by animateLottieCompositionAsState(
        // pass the composition created above
        composition,

        // Iterates Forever
        iterations = LottieConstants.IterateForever,

        // pass isPlaying we created above,
        // changing isPlaying will recompose
        // Lottie and pause/play
        isPlaying = isPlaying,

        // pass speed we created above,
        // changing speed will increase Lottie
        speed = speed,

        // this makes animation to restart
        // when paused and play
        // pass false to continue the animation
        // at which it was paused
        restartOnPlay = false

    )

    LottieAnimation(
        composition,
        progress,
        modifier = Modifier.size(50.dp)
            .clip(CircleShape)

    )
}