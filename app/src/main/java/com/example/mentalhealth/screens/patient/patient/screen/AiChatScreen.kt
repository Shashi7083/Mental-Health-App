package com.example.mentalhealth.screens.patient.patient.screen

import android.app.Instrumentation.ActivityResult
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mentalhealth.screens.patient.patient.SharedViewModel.HomeUiState
import com.example.mentalhealth.screens.patient.patient.SharedViewModel.PatientViewModel
import com.example.mentalhealth.screens.patient.patient.components.TopBar
import com.example.mentalhealth.screens.patient.patient.components.UriCustomSaver
import java.net.URI
import java.util.Locale

@Composable
fun AiChatContentScreen(
    viewModel: PatientViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController : NavHostController
){
    val appUiState = viewModel.uiState.collectAsState()

    AiChatScreen(uiState = appUiState.value , navController = navController){ inpurText , selectedItems->

    }
}


@Composable
fun AiChatScreen(
    uiState: HomeUiState = HomeUiState.Loading,
    navController: NavHostController ,
    onSendClicked : (String, List<Uri>)->Unit,
){



    var userQues  by rememberSaveable() {
        mutableStateOf("")
    }

    var imageUris  = rememberSaveable(saver = UriCustomSaver()) {
        mutableListOf()

    }

    val pickMediaLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()){
        imageUri ->
        imageUri?.let {
            imageUris.add(it)

        }
    }





    Scaffold(
        topBar = {
            TopBar(navController = navController)
        },
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                                         pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

                    },
                        modifier = Modifier.padding(4.dp)) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription ="add" )
                    }

                    // Input Field
                    OutlinedTextField(
                        value = userQues, onValueChange = {
                        userQues = it
                    },
                        label = {
                                Text(text = "User Input")
                        },
                        placeholder = {
                            Text(text = "Upload Image and ask questions")
                        },
                        modifier = Modifier.fillMaxWidth(0.84f)
                    )

                //send button
                    IconButton(onClick = {
                                         if(userQues.isNotBlank()){
                                             onSendClicked(userQues,imageUris)
                                         }
                        userQues = ""
                    },

                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "send"
                        )
                    }
                    

                }
                AnimatedVisibility(visible = imageUris.size>0) {
                    LazyRow(modifier = Modifier.padding(8.dp)) {
                        items(imageUris){imageUri->
                            Column (
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                AsyncImage(model = imageUri,
                                    contentDescription = "",
                                    modifier = Modifier.padding(4.dp)
                                        .requiredSize(50.dp))
                                TextButton(onClick = {
                                    imageUris.remove(imageUri)
                                }) {
                                    Text(text = "Remove")
                                }
                            }
                        }
                    }
                }
            }
        }
    ) {


        Column (
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            when(uiState){
               is HomeUiState.Initial ->{}
               is  HomeUiState.Loading->{
                    Box(contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }

              is  HomeUiState.Success->{
                    Card(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                    ) {
                        Text(text = uiState.ouputText)
                    }
                }

                is  HomeUiState.Error->{
                    Card(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                    ) {
                        Text(text = uiState.error)
                    }
                }

                else ->{

                }
            }
        }

    }
}

