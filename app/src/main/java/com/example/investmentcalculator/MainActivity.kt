package com.example.investmentcalculator

import LumpsumCal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.investmentcalculator.screens.SIPCal
import com.example.investmentcalculator.screens.SplashScreen
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var show by remember { mutableStateOf(false) }


            LaunchedEffect(Unit) {

                delay(3000)

                show = true
            }

            if(!show) {
                SplashScreen()
            }else {
                Buttons()
            }




        }
    }
}

@Preview(showBackground = true)
 @Composable
fun Buttons() {

    val lightGreen = Color(0xFFCCFFCC)
    var sipTextColor by remember { mutableStateOf(Color.Green) }
    var sipButtonColor by remember { mutableStateOf(lightGreen) }
    var lumTextColor by remember { mutableStateOf(Color.White) }
    var lumButtonColor by remember { mutableStateOf(Color.Black) }
    var showSIPCalculator by remember { mutableStateOf(false) }
    var showLumpSumCal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(sipButtonColor),
                onClick = {
                    sipButtonColor = lightGreen
                    sipTextColor = Color.Green
                    lumButtonColor = Color.Black
                    lumTextColor = Color.White
                    showSIPCalculator = true
                    showLumpSumCal = false
                },
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = "SIP",
                    color = sipTextColor,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(lumButtonColor),
                onClick = {
                    lumButtonColor = lightGreen
                    lumTextColor = Color.Green
                    sipButtonColor = Color.Black
                    sipTextColor = Color.White
                    showLumpSumCal = true
                    showSIPCalculator = false
                },
                modifier = Modifier
                    .padding(start = 60.dp)
            ) {
                Text(
                    text = "Lumpsum",
                    color = lumTextColor,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp
                )
            }
        }

        if (showLumpSumCal) {
            LumpsumCal()
        }else {
            SIPCal()
        }

    }
}

