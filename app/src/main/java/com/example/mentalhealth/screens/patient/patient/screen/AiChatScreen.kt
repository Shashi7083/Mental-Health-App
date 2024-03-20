package com.example.mentalhealth.screens.patient.patient.screen

import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.mentalhealth.screens.patient.patient.SharedViewModel.HomeUiState
import com.example.mentalhealth.screens.patient.patient.SharedViewModel.PatientViewModel
import com.example.mentalhealth.screens.patient.patient.components.TopBar
import com.example.mentalhealth.screens.patient.patient.components.UriCustomSaver
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent
import java.net.URI
import java.util.Locale

@Composable
fun AiChatContentScreen(
    viewModel: PatientViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController : NavHostController
){
    val appUiState = viewModel.uiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val imageRequestBuilder = ImageRequest.Builder(LocalContext.current)
    val imageLoader  = ImageLoader.Builder(LocalContext.current).build()

    AiChatScreen(uiState = appUiState.value , navController = navController){ inpurText , selectedItems->
        coroutineScope.launch {
            val bitmaps = selectedItems.mapNotNull {
                val imageRequest = imageRequestBuilder
                    .data(it)
                    .size(size  = 768)
                    .build()

                val imageResult = imageLoader.execute(imageRequest)
                if(imageResult is SuccessResult){
                   return@mapNotNull (imageResult.drawable as BitmapDrawable).bitmap
                }else{
                    return@mapNotNull  null
                }
            }

            viewModel.questioning(userInput = inpurText, selectedImages = bitmaps)
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AiChatScreen(
    uiState: HomeUiState = HomeUiState.Loading,
    navController: NavHostController ,
    onSendClicked : (String, List<Uri>)->Unit,
){



    var userQues  by rememberSaveable() {
        mutableStateOf("")
    }

    var question  by rememberSaveable() {
        mutableStateOf("")
    }



//    var imageUris  = rememberSaveable(saver = UriCustomSaver()) {
//        mutableListOf()
//
//    }

    var imageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }


////this is for old code which is not updating ui
//    val pickMediaLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()){
//        imageUri ->
//        imageUri?.let {
//            imageUris.add(it)
//
//        }
//    }

    val pickMediaLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickMultipleVisualMedia()){
            imageUri ->
        imageUri?.let {
            imageUris = it

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


                    val keyboardController = LocalSoftwareKeyboardController.current
                    // Input Field
                    OutlinedTextField(
                        value = userQues, onValueChange = {
                        userQues = it
                    },
                        label = {
                                Text(text = "User Input")
                        },
                        placeholder = {
                            Text(text = "Upload Image and ask ")
                        },
                        modifier = Modifier.fillMaxWidth(0.84f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Send // Set the IME action to "Send"
                        ),
                        keyboardActions = KeyboardActions(
                            onSend = {


                                // Call the lambda function when the "Send" action is triggered
                                onSendClicked(userQues, imageUris)
                                keyboardController?.hide() // Hide the keyboard when "Send" is pressed
                                question = userQues
                                userQues = ""
                            }
                        )
                    )

                //send button
                    IconButton(onClick = {
                                         if(userQues.isNotBlank()){
                                             onSendClicked(userQues,imageUris)
                                         }
                        question = userQues
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

                    Card (
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ){
                        LazyRow(modifier = Modifier.padding(8.dp)) {
                            items(imageUris){imageUri->
                                Column (
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    AsyncImage(model = imageUri,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .requiredSize(50.dp))
                                    TextButton(onClick = {
//                                    imageUris.remove(imageUri)  //this is for old code which is not updating ui
                                        val uriToRemove = imageUri// Replace with your logic to determine the URI
                                        imageUris = imageUris.filterNot { it == uriToRemove }

                                    }) {
                                        Text(text = "Remove")
                                    }
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
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator()
                    }
                }

              is  HomeUiState.Success->{
                    Card(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(Color.White)
                    ) {

                        Card (
                            modifier = Modifier.fillMaxWidth().padding(2.dp)
                        ){
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                text = "Q. "+question,
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))
                        Card (
                            modifier = Modifier.fillMaxWidth().padding(2.dp)
                        ) {
                            Text(
                                text = uiState.ouputText,
                                modifier = Modifier.padding(8.dp))
                        }
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
                        Text(text = uiState.error,modifier = Modifier.padding(8.dp))
                    }
                }

                else ->{

                }
            }
        }

    }
}

