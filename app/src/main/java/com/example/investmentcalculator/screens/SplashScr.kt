package com.example.investmentcalculator.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.investmentcalculator.R

@Preview
@Composable
fun SplashScreen() {
    var visible by remember { mutableStateOf(false) }

    val enterTransition = fadeIn(animationSpec = tween(durationMillis = 1500, easing = FastOutLinearInEasing))
    val exitTransition = ExitTransition.None

    LaunchedEffect(Unit) {
        visible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        val imagePainter: Painter = painterResource(id = R.drawable.calculator)
        Image(painter = imagePainter, contentDescription = "Investment Cal Image")

        AnimatedVisibility(
            visible = visible,
            enter = enterTransition,
            exit = exitTransition,

        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Investment Calculator",
                color = Color.Green,
                fontSize = 25.sp
            )
        }
    }
}
