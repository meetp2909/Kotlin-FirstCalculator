package org.multicalculator.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

@Composable
fun App() {
    CalcView()
}

@Composable
fun CalcView() {
    val displayText = rememberSaveable { mutableStateOf("0") }
    var leftNumber by rememberSaveable { mutableStateOf(0) }
    var rightNumber by rememberSaveable { mutableStateOf(0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }
    if (complete && operation.isNotEmpty()) {
        val result = when (operation) {
            "+" -> leftNumber + rightNumber
            "-" -> leftNumber - rightNumber
            "*" -> leftNumber * rightNumber
            "/" -> if (rightNumber != 0) leftNumber / rightNumber else "Error"
            else -> "Error"
        }
        displayText.value = result.toString()
        leftNumber = 0
        rightNumber = 0
        operation = ""
        complete = false
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.LightGray)
    ) {
        Row {
            CalcDisplay(display = displayText)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(display = displayText, startNum = i, numButtons = 3)
                }
                Row(
                    modifier = Modifier.padding(0.dp)
                ) {
                    CalcNumericButton(number = 0, display = displayText)
                    CalcEqualsButton(display = displayText, onClick = {
                        // Implement equals logic here
                        complete = true
                    })
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                CalcOperationButton("+", displayText)
                CalcOperationButton("-", displayText)
                CalcOperationButton("*", displayText)
                CalcOperationButton("/", displayText)
            }
        }
    }
}

@Composable
fun CalcDisplay(display: MutableState<String>) {
    Text(
        text = display.value,
        style = MaterialTheme.typography.h4,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.End
    )
}

@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons
    Row(
        modifier = Modifier.padding(0.dp)
    ) {
        for (i in startNum until endNum) {
            CalcNumericButton(number = i, display = display)
        }
    }
}

@Composable
fun CalcOperationColumn(display: MutableState<String>) {
    Column {
        CalcOperationRow(display = display)
    }
}

@Composable
fun CalcOperationRow(display: MutableState<String>) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalcOperationButton(operation = "+", display = display)
        CalcOperationButton(operation = "-", display = display)
        CalcOperationButton(operation = "*", display = display)
        CalcOperationButton(operation = "/", display = display)
    }
}

@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>) {
    Button(
        onClick = {
            if (display.value == "0") {
                display.value = number.toString()
            } else {
                display.value += number.toString()
            }
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>) {
    Button(
        onClick = {
            display.value += operation
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(display: MutableState<String>, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(4.dp)) {
        Text(text = "=")
    }
}
