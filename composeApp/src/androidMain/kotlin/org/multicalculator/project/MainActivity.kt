package org.multicalculator.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
    CalcView()
}

@Composable
fun CalcView() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Simple Calculator", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {}) {
            Text(text = "Click me")
        }
        Spacer(modifier = Modifier.height(16.dp))
        CalcRow()
        Spacer(modifier = Modifier.height(16.dp))
        CalcOperationRow()
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
fun CalcRow() {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalcNumericButton("1") { /* Handle button click */ }
        CalcNumericButton("2") { /* Handle button click */ }
        CalcNumericButton("3") { /* Handle button click */ }
    }
}

@Composable
fun CalcOperationRow() {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalcOperationButton("+") {}
        CalcOperationButton("-") {}
        CalcOperationButton("*") {}
        CalcOperationButton("/") {}
    }
}

@Composable
fun CalcNumericButton(number: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = number)
    }
}

@Composable
fun CalcOperationButton(operation: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = operation)
    }
}
@Composable
fun CalcEqualsButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "=")
    }
}