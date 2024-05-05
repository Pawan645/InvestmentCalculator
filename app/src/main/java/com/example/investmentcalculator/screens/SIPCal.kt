package com.example.investmentcalculator.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

@OptIn(ExperimentalComposeUiApi::class)
//@Preview(showBackground = true)
@Composable
fun SIPCal() {
    var monthlyInv by remember { mutableStateOf("") }
    var returnRate by remember { mutableStateOf("") }
    var timePeriod by remember { mutableStateOf("") }
    var investedAmt by remember { mutableIntStateOf(0) }
    var estRate by remember { mutableIntStateOf(0) }
    var totalValue by remember { mutableIntStateOf(0) }
    var isClicked by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally    ) {

        OutlinedTextField(modifier = Modifier
            .padding(top = 15.dp)
            .border(3.dp, Color.Green),
            value = monthlyInv,
            onValueChange = { newValue ->
                val enteredValue = newValue.toLongOrNull() ?: 0L
                if (enteredValue <= 1000000L) {
                    monthlyInv = newValue
                } },
            label = {
                Text(
                    text = "Monthly Inv",
                    color = Color.Green
                )
            },
            textStyle = LocalTextStyle.current.copy(color = Color.Green),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(2.dp))

        OutlinedTextField(modifier = Modifier
            .padding(top = 20.dp)
            .border(3.dp, Color.Green),
            value = returnRate,
            onValueChange = { newValue ->
                val enteredValue = newValue.toLongOrNull() ?: 0L
                if (enteredValue <= 30) {
                    returnRate = newValue
                } },
            label = {
                Text(
                    text = "Return Rate(p.a)",
                    color = Color.Green
                )
            },
            textStyle = LocalTextStyle.current.copy(color = Color.Green),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )

        Spacer(modifier = Modifier.padding(2.dp))

        OutlinedTextField(modifier = Modifier
            .padding(top = 20.dp)
            .border(3.dp, Color.Green),
            value = timePeriod,
            onValueChange = { newValue ->
                val enteredValue = newValue.toLongOrNull() ?: 0L
                if (enteredValue <= 40L) {
                    timePeriod = newValue
                } },
            label = {
                Text(
                    text = "Time Period(Yr.)",
                    color = Color.Green
                )
            },
            textStyle = LocalTextStyle.current.copy(color = Color.Green),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        val context = LocalContext.current

        Button(modifier = Modifier
            .padding(top = 20.dp)
            .size(140.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(Color.Green),
            onClick = {
                val monthlyInvv = monthlyInv.toDoubleOrNull() ?: 0.0
                val returnRatee = returnRate.toDoubleOrNull() ?: 0.0
                val duration = timePeriod.toIntOrNull() ?: 0

                val ratePerPeriod = (returnRatee / 12) / 100
                val totalPeriods = 12 * duration

                if (monthlyInv.isEmpty() || returnRate.isEmpty() || timePeriod.isEmpty()) {

                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()

                } else if (monthlyInv.toInt() < 1 || returnRate.toInt() < 1 || timePeriod.toInt() < 1) {

                    Toast.makeText(context, "All fields should be greater than 0", Toast.LENGTH_SHORT).show()

                }else{
                    investedAmt = (monthlyInvv * totalPeriods).toInt()
                    val factor = (monthlyInvv*((1 + ratePerPeriod).pow(totalPeriods.toDouble()) -1)).toInt()
                    totalValue = (factor * (1+ratePerPeriod)/ratePerPeriod).toInt()
                    estRate = totalValue - investedAmt


                    isClicked = true
                }


                keyboardController?.hide()



            }) {
            Text(
                text = "Calculate",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        if(isClicked) {

            Divider(color = Color.Green, thickness = 3.dp)
            Text(
                text = "Calculated Inv",
                modifier = Modifier
                    .padding(top = 6.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Green
            )
            Spacer(modifier = Modifier.padding(10.dp))


            val formattedInvestedAmount = NumberFormat.getNumberInstance(Locale("en", "IN")).format(investedAmt)

            Row {
                OutlinedTextField(modifier = Modifier
                    .border(3.dp, Color.Green),
                    value = "₹${formattedInvestedAmount}",
                    onValueChange = {},
                    label = {
                        Text(
                            text = "Invested Amount",
                            color = Color.Green
                        )
                    },

                    textStyle = LocalTextStyle.current.copy(color = Color.Green),
                )
            }


            Spacer(modifier = Modifier.padding(8.dp))

            val formattedEstRate = NumberFormat.getNumberInstance(Locale("en", "IN")).format(estRate)

            OutlinedTextField(modifier = Modifier
                .border(3.dp, Color.Green),
                value = "₹${formattedEstRate}",
                onValueChange = {},
                label = {
                    Text(
                        text = "Est. Returns",
                        color = Color.Green
                    )
                },

                textStyle = LocalTextStyle.current.copy(color = Color.Green),

                )


            Spacer(modifier = Modifier.padding(8.dp))

            val formattedTotalValue = NumberFormat.getNumberInstance(Locale("en", "IN")).format(totalValue)

            Row {
                OutlinedTextField(modifier = Modifier
                    .border(3.dp, Color.Green),
                    value = "₹${formattedTotalValue}",
                    onValueChange = {},
                    label = {
                        Text(
                            text = "Total Value",
                            color = Color.Green
                        )
                    },

                    textStyle = LocalTextStyle.current.copy(color = Color.Green),
                )
            }

        }
    }
}