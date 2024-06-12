package org.multicalculator.project

import App
import android.os.Bundle
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

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
        CalcRow() // Using the CalcRow composable function
    }
}

@Composable
fun CalcRow() {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { /* Handle button click */ }) {
            Text(text = "1")
        }
        Button(onClick = { /* Handle button click */ }) {
            Text(text = "2")
        }
        Button(onClick = { /* Handle button click */ }) {
            Text(text = "3")
        }
    }
}
