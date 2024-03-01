package com.example.mentalhealth.screens.patient.patient.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.ui.linechart.LineChart
import com.example.mentalhealth.R


@Composable
fun Meditation(){

    Box(modifier = Modifier
        .fillMaxHeight(0.38f)
        .fillMaxWidth()
        .shadow(elevation = 1.dp, shape = RoundedCornerShape(10.dp)),
    ){

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(start = 10.dp, end = 10.dp, top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "Meditation", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Relax your mind now" , fontSize = 12.sp)
                    Text(text = "Description" , fontSize = 12.sp, color = Color.Green
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.medgirl1),
                    contentDescription =null,
                    modifier = Modifier.height(200.dp).width(200.dp)
                    )



            }


        }
    }
}