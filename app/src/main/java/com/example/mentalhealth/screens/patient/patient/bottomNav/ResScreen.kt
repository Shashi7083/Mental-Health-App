package com.example.mentalhealth.screens.patient.patient.bottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.mentalhealth.sampleApi.retrofit.Post
import com.example.mentalhealth.sampleApi.ui.SampleViewModel
import com.example.mentalhealth.sampleApi.util.ApiState
import com.example.mentalhealth.screens.patient.patient.components.BottomBarPatient
import com.example.mentalhealth.screens.patient.patient.components.TopBar

@Composable
fun ResScreen(
    navController: NavHostController,
    viewModel: SampleViewModel
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
            ){
            GetData(sampleViewModel = viewModel)
        }
    }
}




@Composable
fun GetData(sampleViewModel : SampleViewModel){
    when(val result = sampleViewModel.response.value){
        is ApiState.Success ->{
                LazyColumn{
                    items(result.data){
                            ArticleView(post = it)
                    }
                }
        }

        is ApiState.Failure->{
            Text(text = result.msg.toString())
        }

        ApiState.Loading->{
            CircularProgressIndicator()
        }

        ApiState.Empty->{
            
        }

        else -> {}
    }
}


@Composable
fun ArticleView(
    post : Post
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Cyan.copy(alpha = 0.3f))){
        Text(text = post.body)
    }
}