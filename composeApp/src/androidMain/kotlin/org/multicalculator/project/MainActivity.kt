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
    var display by remember { mutableStateOf("") }
    CalcView(display = display, onDisplayChange = { display = it })
}

@Composable
fun CalcView(display: String, onDisplayChange: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Simple Calculator", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        CalcDisplay(value = display)
        Spacer(modifier = Modifier.height(16.dp))
        CalcRow(onDisplayChange = onDisplayChange)
        Spacer(modifier = Modifier.height(16.dp))
        CalcOperationRow(display = display, onDisplayChange = onDisplayChange)
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
fun CalcRow(onDisplayChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalcNumericButton("1", onClick = { onDisplayChange("1") })
        CalcNumericButton("2", onClick = { onDisplayChange("2") })
        CalcNumericButton("3", onClick = { onDisplayChange("3") })
        CalcNumericButton("4", onClick = { onDisplayChange("4") })
        CalcNumericButton("5", onClick = { onDisplayChange("5") })
        CalcNumericButton("6", onClick = { onDisplayChange("6") })
        CalcNumericButton("7", onClick = { onDisplayChange("7") })
        CalcNumericButton("8", onClick = { onDisplayChange("8") })
        CalcNumericButton("9", onClick = { onDisplayChange("9") })
        CalcNumericButton("0", onClick = { onDisplayChange("0") })

    }
}

@Composable
fun CalcOperationRow(display: String, onDisplayChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalcOperationButton("+", display = display, onDisplayChange = onDisplayChange)
        CalcOperationButton("-", display = display, onDisplayChange = onDisplayChange)
        CalcOperationButton("*", display = display, onDisplayChange = onDisplayChange)
        CalcOperationButton("/", display = display, onDisplayChange = onDisplayChange)
    }
}

@Composable
fun CalcNumericButton(number: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = number)
    }
}

@Composable
fun CalcOperationButton(operation: String, display: String, onDisplayChange: (String) -> Unit) {
    Button(onClick = {
        onDisplayChange(display + operation)
    }) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "=")
    }
}
