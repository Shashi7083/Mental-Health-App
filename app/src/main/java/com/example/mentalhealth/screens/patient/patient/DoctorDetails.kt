package com.example.mentalhealth.screens.patient.patient

import android.app.DatePickerDialog
import android.view.WindowManager
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.mentalhealth.screens.patient.SharedViewModel.PatientViewModel
import com.example.mentalhealth.screens.patient.patient.bottomNav.DoctorModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun DoctorDetails(
    navController : NavHostController,
    viewModel: PatientViewModel
){
    val doctor : DoctorModel = viewModel.doc.value

    val isSystemInDarkMode = isSystemInDarkTheme()
    val systemController  = rememberSystemUiController()

    val context = LocalContext.current as? ComponentActivity
    var date by remember{ mutableStateOf("Your Date") }
    var time by remember { mutableStateOf("12.30 pm") }

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = context?.let {
        DatePickerDialog(
            it,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                date = "$mDayOfMonth/${mMonth + 1}/$mYear"
            }, mYear, mMonth, mDay
        )
    }


    LaunchedEffect(key1 = true) {
        systemController.setStatusBarColor(Color.Transparent)
        context?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        val window = (context as Activity).window
//        window.addFlags(WindowManager.LayoutParams.FL)
//        delay(1000) // Auto-hide after 1 second
//        systemController.setStatusBarColor(Color.Transparent)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    DisposableEffect(key1 = navController.currentBackStackEntry) {
        val callback = NavController.OnDestinationChangedListener { _, _, _ ->
            systemController.setStatusBarColor(Color.White)
            context?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        navController.addOnDestinationChangedListener(callback)
        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }

    var showBottomSheet = remember{ mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ){

     BottomSheetPrice(showBottomSheet = showBottomSheet)
        
        Image(
            painter = rememberAsyncImagePainter(model = doctor.img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.48f)
                .background(Color.Blue.copy(alpha = 0.05f), shape = RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillWidth,

        )

        Row(
            modifier = Modifier
                .padding(top = 60.dp, start = 15.dp)
                .height(40.dp)
                .width(40.dp)
                .background(color = Color.LightGray.copy(alpha = 0.5f), shape = CircleShape)
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        }

        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize()
                .padding(top = 320.dp)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
                )
                .padding(top = 30.dp, start = 15.dp, end = 15.dp)
        ){
           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(start = 10.dp, end = 10.dp)
                   .verticalScroll(rememberScrollState())
           ) {
               Row(
                   horizontalArrangement = Arrangement.Center,
                   modifier = Modifier.fillMaxWidth()
               ) {
                   Box(modifier = Modifier
                       .height(10.dp)
                       .width(50.dp)
                       .background(Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(5.dp)))
               }

               Spacer(modifier = Modifier.height(20.dp))
               Text(
                   text = doctor.name,
                   fontSize = 23.sp,
                   fontWeight = FontWeight.Bold,
                   modifier = Modifier.fillMaxWidth(),
                   textAlign = TextAlign.Center
               )

               Spacer(modifier = Modifier.height(20.dp))
               Text(
                   text = doctor.specialization,
                   fontSize = 18.sp,
                   fontWeight = FontWeight.SemiBold,
                   color = Color(0xFF9C9C9C)

               )
               Spacer(modifier = Modifier.height(12.dp))

               Text(
                   text = doctor.degree,
                   fontSize = 18.sp,
                   fontWeight = FontWeight.Bold,
                   color = Color(0xff4a4a4a)

               )

               Spacer(modifier = Modifier.height(12.dp))
               Row(
                   verticalAlignment = Alignment.CenterVertically
               ) {
                  Icon(imageVector = Icons.Filled.LocationOn, contentDescription = null,
                      tint = Color(0xff949494))
                   Spacer(modifier = Modifier.width(5.dp))
                   Text(
                       text = "Darbhanga Medical College",
                       fontSize = 18.sp,
                       fontWeight = FontWeight.SemiBold,
                       color = Color(0xFF9C9C9C)

                   )
               }

               Spacer(modifier = Modifier.height(30.dp))

               Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .fillMaxHeight(0.2f),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween
               ){

                   DoctorCareer(title = "Patient", value = doctor.patient.toString())
                   Box(
                       modifier = Modifier
                           .height(65.dp)
                           .width(3.dp)
                           .padding(top = 8.dp, bottom = 8.dp)
                           .background(Color.Black.copy(alpha = 0.4f))
                          
                   )
                   DoctorCareer(title = "Experience", value = doctor.exp.toString()+" "+"Years+")
                   Box(
                       modifier = Modifier
                           .height(65.dp)
                           .width(3.dp)
                           .padding(top = 8.dp, bottom = 8.dp)
                           .background(Color.Black.copy(alpha = 0.4f))

                   )
                   
                   DoctorCareer(
                       title = "Rating",
                       value = doctor.rating ,
                       count = doctor.ratingCount.toString(),
                       icon = Icons.Filled.Star,
                       iconColor = Color(0xFFFFC107))
               }

               Spacer(modifier = Modifier.height(30.dp))
               Row(
                   horizontalArrangement = Arrangement.SpaceBetween,
                   verticalAlignment = Alignment.CenterVertically,
                   modifier = Modifier.fillMaxWidth()
               ) {
                   Text(
                       text = "Pick Your Date",
                       fontSize = 20.sp,
                       fontWeight = FontWeight.Bold,
                       color = Color(0xff4a4a4a)
                   )

                   Row (
                       verticalAlignment = Alignment.CenterVertically
                   ){
                       Column (
                           modifier = Modifier.padding(2.dp)
                       ){

                           Text(
                               text = date,
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 16.sp,
                               color = Color(0xFF9C9C9C),
                              // textDecoration = TextDecoration.Underline,
                               modifier = Modifier.width(150.dp),
                               textAlign = TextAlign.Center
                           )
                           Spacer(modifier = Modifier.height(1.dp))
                           Box(
                               modifier = Modifier
                                   .width(150.dp)
                                   .height(1.dp)
                                   .background(Color(0xFF9C9C9C))
                           )
                       }

                       IconButton(onClick = {
                           mDatePickerDialog!!.show()
                       }) {
                           Icon(
                               imageVector = Icons.Filled.DateRange,
                               contentDescription = "Calendar",
                               tint = Color(0xFF9C9C9C)
                           )
                       }
                   }
               }
               Spacer(modifier = Modifier.height(30.dp))
               
               Column(
                   verticalArrangement = Arrangement.spacedBy(15.dp),
                   modifier = Modifier.fillMaxWidth()
               ) {
                   Text(
                       text = "Available Time Slot",
                       fontWeight = FontWeight.Bold,
                       fontSize = 20.sp,
                       color = Color(0xff4a4a4a)
                   )

                   Box(modifier = Modifier
                       .fillMaxWidth()
                       .height(160.dp)){
                       LazyHorizontalGrid(
                           modifier = Modifier.fillMaxWidth(),
                           rows = GridCells.Fixed(2),

                           horizontalArrangement = Arrangement.spacedBy(20.dp),
                           verticalArrangement = Arrangement.spacedBy(12.dp)
                       )
                       {
                           items(doctor.timeSlots){ t->
                               TimeSlotView(time = t ,selected = time ){
                                   time = it
                               }
                           }
                       }
                   }

                   Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(130.dp)
                           .padding(top = 30.dp, bottom = 30.dp)
                           .clip(RoundedCornerShape(40.dp))
                           .background(color = Color(0xfff77b59))
                           .clickable {
                               showBottomSheet.value = true
                           },
                       contentAlignment = Alignment.Center
                   ){
                       Text(
                           text = "Book appointment",
                           fontWeight = FontWeight.Bold,
                           fontSize = 20.sp,
                           color = Color.White
                       )
                   }
               }


           }
        }


    }
}


@Composable
fun TimeSlotView(
    time : String,
    selected : String,
    onSelect:(String)->Unit
){
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(

                if (selected.equals(time)) Color(0xfff77b59) else Color.White
            )
            .clickable {
                onSelect(time)
            },
        contentAlignment = Alignment.Center

    ){
        Text(
            text = time,
            color =  if(selected.equals(time))Color.White else Color(0xff4a4a4a),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp)
    }
}

@Composable
fun DoctorCareer(
    title: String,
    value: String,
    icon: ImageVector? = null,
    count: String? = null,
    iconColor : Color = Color.Black
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
    ){
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF9C9C9C),
            fontSize = 18.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if(icon != null){
                Icon(imageVector = icon, contentDescription = null, tint = iconColor)
            }
            Text(
                text = value,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff4a4a4a),
                fontSize = 20.sp
            )

            if(count != null){
                Text(
                    text = "(${count})",
                    fontSize = 20.sp)
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetPrice(
    showBottomSheet : MutableState<Boolean>
){
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if(showBottomSheet.value){
        ModalBottomSheet(
            onDismissRequest = { /*TODO*/ },
            sheetState = sheetState,
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Hello")
                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet.value = false
                        }
                    }
                }) {
                    androidx.compose.material3.Text("Hide bottom sheet")
                }
            }

        }
    }
}