package com.example.mentalhealth.screens.patient.patient.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealth.R
import com.example.mentalhealth.model.StartQuestion
import com.example.mentalhealth.model.questions
import com.example.mentalhealth.navGraph.BottomNavScreen
import com.example.mentalhealth.navGraph.Route
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuestionsScreen(
    navController: NavHostController
){

    val pagerState = rememberPagerState(
        initialPage = 0,
    ) {
        questions.size
    }

    val buttonState = remember{
        derivedStateOf {
            when(pagerState.currentPage){
                0-> listOf("","Next")
                questions.size-1-> listOf("Back","Continue")
                else -> listOf("Back","Next")
            }

        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.questionbg),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,

            contentDescription = null)

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f)))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(state = pagerState) {
                QuestionView(page = questions.get(it), currentPage = it)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(end = 30.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.End
            ){
                val scope = rememberCoroutineScope()

                if (buttonState.value.get(0).isNotEmpty()) {
                    NewsTextButton(text = buttonState.value[0], onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    })
                }

                NewsButton(text = buttonState.value[1], onClick = {
                    scope.launch {
                        if (pagerState.currentPage == questions.size-1) {
                                navController.navigate(BottomNavScreen.HomeScreen.route){
                                    popUpTo(BottomNavScreen.HomeScreen.route){
                                        inclusive = true
                                    }
                                }
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                })

            }
        }

    }
}







@Composable
fun QuestionView(
    modifier :Modifier = Modifier,
    page : StartQuestion,
    currentPage : Int
){
    Column(
        modifier = modifier.padding(top=10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(text = "( ${currentPage+1} of ${questions.size} )", style = TextStyle(fontWeight = FontWeight.SemiBold))


        Text(text = page.question, style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        ),
            modifier = Modifier.padding(start=10.dp , end = 10.dp))

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ){
            items(page.options){option->
                OptionView(option = option)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun OptionView(
    option :String
){
   Box(modifier = Modifier
       .fillMaxWidth(0.7f)
       .padding(4.dp)){
       Column(
           modifier=Modifier.fillMaxWidth()
       ) {
           Box(modifier = Modifier
               .background(Color.Black.copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp))
               .padding(10.dp)
               .fillMaxWidth(),
               ){
               Text(text = option, fontSize = 18.sp , color = Color.White, fontWeight = FontWeight.SemiBold)
           }

       }
   }
}



@Composable
fun NewsButton(
    text: String,
    onClick:()->Unit
){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ),
        shape = RoundedCornerShape(size=6.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))
    }
}

@Composable
fun NewsTextButton(
    text:String,
    onClick: () -> Unit
){
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.Black
        )
    }
}