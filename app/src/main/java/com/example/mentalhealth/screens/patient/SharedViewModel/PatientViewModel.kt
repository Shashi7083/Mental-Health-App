package com.example.mentalhealth.screens.patient.SharedViewModel

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.screens.patient.patient.bottomNav.DoctorModel
import com.example.mentalhealth.screens.patient.patient.screen.PatientProfileScreen
import com.google.ai.client.generativeai.BuildConfig

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PatientViewModel:ViewModel() {

    private val _docDetail: MutableState<DoctorModel> = mutableStateOf(DoctorModel("","","",0,"",""))
    val doc : State<DoctorModel> = _docDetail

    fun setData( data : DoctorModel){
        _docDetail.value = data
    }

    //geminie

    private val _uiState:MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Initial)
    val uiState = _uiState.asStateFlow()

    // Function to update the UI state
    fun updateUiState(newState: HomeUiState) {
        viewModelScope.launch {
            _uiState.emit(newState)
        }
    }

    private  var generativeModel : GenerativeModel


    init{
        val config = generationConfig{
            temperature = 0.7f
        }

         generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = com.example.mentalhealth.BuildConfig.API_KEY,
            generationConfig = config
        )
    }

    fun questioning(userInput : String, selectedImages: List<Bitmap>){
        _uiState.value = HomeUiState.Loading
        val prompt = "Take a look at images, and then answer the following question $userInput"

        viewModelScope.launch(Dispatchers.IO){
            try {
                val content = content {
                    for (bitmap in selectedImages){
                        image(bitmap)
                    }
                    text(prompt)

                }

                var output = ""
                generativeModel.generateContentStream(content).collect(){
                        output += it.text
                    _uiState.value = HomeUiState.Success(output)
                }

            }catch (e: Exception){
                _uiState.value =
                    HomeUiState.Error(e.localizedMessage ?: "Error in Generating content")
            }
        }

        }
}



//for gemini
sealed interface HomeUiState{
    object Initial : HomeUiState
    object Loading : HomeUiState
    data class Success(
        val ouputText: String
    ) : HomeUiState
    data class Error (val error:String): HomeUiState
}



