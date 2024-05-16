
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun LumpsumCal() {
    var returnRate by remember { mutableStateOf("") }
    var timePeriod by remember { mutableStateOf("") }
    var investedAmt by remember { mutableStateOf("") }
    var estRate by remember { mutableStateOf(0L) }
    var totalValue by remember { mutableStateOf(0L) }
    var isClicked by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 15.dp)
                .border(3.dp, Color.Green),
            value = investedAmt,
            onValueChange = { newValue ->
                val enteredValue = newValue.toLongOrNull() ?: 0L
                if (enteredValue <= 100000000L) {
                    investedAmt = newValue
                }
            },
        label = {
                Text(
                    text = "Total Inv",
                    color = Color.Green
                )
            },
            textStyle = LocalTextStyle.current.copy(color = Color.Green),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(2.dp))

        OutlinedTextField(
            modifier = Modifier
                .padding(top = 20.dp)
                .border(3.dp, Color.Green),
            value = returnRate,
            onValueChange = {
                    newValue ->
                val enteredValue = newValue.toLongOrNull() ?: 0L
                if (enteredValue <= 30) {
                    returnRate = newValue
                }
            },
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

        OutlinedTextField(
            modifier = Modifier
                .padding(top = 20.dp)
                .border(3.dp, Color.Green),
            value = timePeriod,
            onValueChange = { newValue ->
                val enteredValue = newValue.toLongOrNull() ?: 0L
                if (enteredValue <= 40) {
                    timePeriod = newValue
                }
            },
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

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(140.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(Color.Green),
            onClick = {

                val investedAmount = investedAmt.toLongOrNull() ?: 0L
                val returnRateValue = returnRate.toDoubleOrNull() ?: 0.0
                val duration = timePeriod.toFloatOrNull() ?: 0.0f

                if (investedAmt.isEmpty() || returnRate.isEmpty() || timePeriod.isEmpty()) {

                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()

                }else if (investedAmount < 1 || returnRateValue < 1 || duration < 1) {
                    Toast.makeText(context, "All fields should be greater than 0", Toast.LENGTH_SHORT).show()
                } else {
                    val rate = 1 + returnRateValue / 100
                    estRate = ((investedAmount * rate.pow(duration.toDouble())) - investedAmount).toLong()
                    totalValue = investedAmount + estRate
                    isClicked = true
                }

                keyboardController?.hide()
            }
        ) {
            Text(
                text = "Calculate",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        if (isClicked) {

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

            val formattedInvAmt = NumberFormat.getNumberInstance(Locale("en", "IN")).format(investedAmt.toInt())

            Row {
                OutlinedTextField(modifier = Modifier
                    .border(3.dp, Color.Green),
                    value = "₹${formattedInvAmt}",
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
