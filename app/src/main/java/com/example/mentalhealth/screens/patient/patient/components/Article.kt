package com.example.mentalhealth.screens.patient.patient.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.ui.linechart.LineChart
import com.example.mentalhealth.R


@Composable
fun Articles(){



    Box(modifier = Modifier
        .height(200.dp)
        .fillMaxWidth()
        .padding(bottom = 10.dp)
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
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Health Article" ,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row (
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.articleimg),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .width(130.dp)
                            .clip(RoundedCornerShape(8.dp))
                        
                        )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Government Program",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xffe68830)
                        )

                        Text(
                            text = "According to the Anxiety and Depression Association of America, anxiety disorders are the most commonmental illness. People with these",
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }


            }


        }
    }

}