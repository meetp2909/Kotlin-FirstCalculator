package org.multicalculator.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    val display = remember { mutableStateOf("") }
    CalcView(display = display)
}

@Composable
fun CalcView(display: MutableState<String>) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Simple Calculator", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        CalcDisplay(value = display.value)
        Spacer(modifier = Modifier.height(16.dp))
        CalcRow(display = display)
        Spacer(modifier = Modifier.height(16.dp))
        CalcOperationRow(display = display)
        Spacer(modifier = Modifier.height(16.dp))
        CalcEqualsButton(onClick = { /* Handle equals click */ })
    }
}

@Composable
fun CalcDisplay(value: String) {
    Text(
        text = value,
        style = MaterialTheme.typography.h4,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.End
    )
}

@Composable
fun CalcRow(display: MutableState<String>) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalcNumericButton(number = 1, display = display)
        CalcNumericButton(number = 2, display = display)
        CalcNumericButton(number = 3, display = display)
        CalcNumericButton(number = 4, display = display)
        CalcNumericButton(number = 5, display = display)
        CalcNumericButton(number = 6, display = display)
        CalcNumericButton(number = 7, display = display)
        CalcNumericButton(number = 8, display = display)
        CalcNumericButton(number = 9, display = display)


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
            display.value += number.toString()
        }
    ) {
        Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>) {
    Button(
        onClick = { /* Empty onClick */ },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "=")
    }
}
