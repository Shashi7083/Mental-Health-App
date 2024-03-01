package com.example.mentalhealth.screens.patient.patient.bottomNav

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.mentalhealth.R
import com.example.mentalhealth.navGraph.Route
import com.example.mentalhealth.screens.patient.patient.SharedViewModel.PatientViewModel
import com.example.mentalhealth.screens.patient.patient.components.BottomBarPatient
import com.example.mentalhealth.screens.patient.patient.components.TopBar

@Composable
fun DoctorsListScreen(
    navController : NavHostController,
    viewModel: PatientViewModel
){



    val doctorTypes = listOf(
        DocTypeModel(
            icon = R.drawable.pcp,
            name = "PCP",
            color =  Color(0xffbeafc7)
        ),
        DocTypeModel(
            icon = R.drawable.psychiatrist,
            name = "Psychiatrist",
            color =  Color(0xff80d9ff)
        ),
        DocTypeModel(
            icon = R.drawable.psychologist,
            name = "Pshchologist",
            color =  Color(0xffeda6a1)
        ),
        DocTypeModel(
            icon = R.drawable.therapist,
            name = "Therapist",
            color =  Color(0xffe3e5fc)
        ),
    )

    val doctors = listOf(
        DoctorModel(
            name = "Dr. John Doe",
            degree = "MD",
            specialization = "Psychiatry",
            exp = 10,
            img = "https://www.sonicseo.com/wp-content/uploads/2020/07/surgeon-768x768.jpg",
            docMessage = "Welcome to my profile."
        ),
        DoctorModel(
            name = "Dr. Jane Smith",
            degree = "PhD",
            specialization = "Clinical Psychology",
            exp = 8,
            img = "https://th.bing.com/th/id/OIP.ob_IsYLQJMOWnHpe7sWI8wHaHo?w=182&h=187&c=7&r=0&o=5&pid=1.7\n" +
                    "/sa/simg/Flag_Feedback.png",
            docMessage = "Feel free to reach out for a consultation."
        ),
        // Add more dummy data here
        DoctorModel(
            name = "Dr. Michael Johnson",
            degree = "MD",
            specialization = "Child Psychiatry",
            exp = 12,
            img = "https://th.bing.com/th/id/R.b39b602151bf94f9d11d35fb584c3aa3?rik=xDw4cPC717zjwg&riu=http%3a%2f%2fpluspng.com%2fimg-png%2fpng-woman-doctor-health-savings-accounts-667.png&ehk=wrIZSF%2bS2XBaB7pPsBquckdZZkP6k9AFk5eLryrg%2f24%3d&risl=&pid=ImgRaw&r=0"
        ),
        DoctorModel(
            name = "Dr. Emily Wilson",
            degree = "PhD",
            specialization = "Behavioral Therapy",
            exp = 15,
            img = "https://images.theconversation.com/files/304957/original/file-20191203-66986-im7o5.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=926&fit=clip"
        ),
        DoctorModel(
            name = "Dr. David Brown",
            degree = "MD",
            specialization = "Addiction Psychiatry",
            exp = 7,
            img = "https://th.bing.com/th/id/OIP.OhbJqC2_QeXqutiFPI5FZAHaFP?w=277&h=196&c=7&r=0&o=5&pid=1.7"
        ),
        DoctorModel(
            name = "Dr. Sarah Lee",
            degree = "MD",
            specialization = "Geriatric Psychiatry",
            exp = 9,
            img = "https://th.bing.com/th/id/OIP.wgwfONnwE6QHBNcxzOFbHAHaE7?w=219&h=180&c=7&r=0&o=5&pid=1.7"
        ),
        DoctorModel(
            name = "Dr. Robert Martinez",
            degree = "MD",
            specialization = "Forensic Psychiatry",
            exp = 11,
            img = "https://thumbs.dreamstime.com/b/doctor-work-reading-document-hospital-room-44544749.jpg"
        ),
        DoctorModel(
            name = "Dr. Laura Taylor",
            degree = "PhD",
            specialization = "Cognitive-Behavioral Therapy",
            exp = 13,
            img = "https://thumbs.dreamstime.com/b/hospital-medicine-doctor-work-115750658.jpg"
        )
    )

    Scaffold(
        topBar = {
                 TopBar(navController = navController)
        },
        bottomBar = {
            BottomBarPatient(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = 10.dp, start = 15.dp, end = 15.dp)
        )
        {

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(35.dp)
            ){
                items(doctorTypes){type->
                    DoctorsListView(doctor = type, navController = navController)

                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues( vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(35.dp)
            ){

                items(doctors){doctor ->
                    DoctorView(doctor = doctor, navController = navController, onClick = {
                        viewModel.setData(it)
                    })
                }
            }

        }
    }
}




data class DoctorModel(
    val name :String,
    val degree : String,
    val specialization : String,
    val exp : Int,
    val img : String,
    val docMessage : String ="",
    val patient : Int = 200,
    val rating : String = "4.2",
    val ratingCount : Int = 276,
    val timeSlots : List<String> = listOf(
        "12.30 pm" ,"13.00 pm", "13.30 pm", "16.00 pm", "16.30 pm", "17.00 pm"
    )
)


data class DocTypeModel(
    val icon: Int ,
    val name : String,
    val color: Color,
)

@Composable
fun DoctorsListView(
  doctor : DocTypeModel,
  navController: NavHostController,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(modifier = Modifier
            .border(
                border = BorderStroke(2.dp, doctor.color),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(6.dp)
            .background(color = doctor.color, shape = RoundedCornerShape(10.dp))
            .padding(5.dp))
        {
            Image(
                painter = painterResource(id = doctor.icon),
                contentDescription = "doctor icon" ,
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp),
                contentScale = ContentScale.Fit
                )
        }
        Text(text = doctor.name, fontSize = 12.sp)

    }
}


@Composable
fun DoctorView(
    doctor : DoctorModel,
    navController : NavHostController,
    onClick : (DoctorModel)->Unit
){
    Column(
        modifier = Modifier,
    ) {
        Box(
            modifier = Modifier
                .background(Color.Blue.copy(alpha = 0.05f), shape = RoundedCornerShape(16.dp))
                .height(180.dp)
                .width(180.dp)
                .clickable {
                    onClick(doctor)
                    navController.navigate(Route.DoctorDetails.route)
                }
        ){


            Image(
                painter = rememberAsyncImagePainter(doctor.img),
                contentDescription = "image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = doctor.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xff474747)
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = doctor.specialization,
            style = TextStyle(
                color = Color(0xFF999999),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = doctor.degree,
            style = TextStyle(
                color = Color(0xFF999999),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107)
                )
                Text(
                    text = "4.2",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff474747)
                    )

                Text(
                    text = " (234)",
                    color = Color(0xff474747))
            }

            Row {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = Color(0xff8f8f8f)
                )

                Text(
                    text = "234",
                    color = Color(0xff474747),
                    fontWeight = FontWeight.Bold)
            }
        }

    }
}