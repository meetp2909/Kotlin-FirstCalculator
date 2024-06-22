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
        var answer = 0
        when (operation) {
            "+" -> answer = leftNumber + rightNumber
            "-" -> answer = leftNumber - rightNumber
            "*" -> answer = leftNumber * rightNumber
            "/" -> if (rightNumber != 0) answer = leftNumber / rightNumber else displayText.value = "Error"
        }
        displayText.value = answer.toString()
        leftNumber = 0
        rightNumber = 0
        operation = ""
        complete = false
    } else if (operation.isNotEmpty() && !complete) {
        rightNumber = displayText.value.toInt()
        displayText.value = rightNumber.toString()
        complete = true
    } else {
        leftNumber = displayText.value.toInt()
        displayText.value = leftNumber.toString()
    }

    fun numberPress(btnNum: Int) {
        if (complete) {
            leftNumber = 0
            rightNumber = 0
            operation = ""
            complete = false
            displayText.value = btnNum.toString()
        } else {
            if (operation.isNotBlank() && !complete) {
                rightNumber = rightNumber * 10 + btnNum
                displayText.value = rightNumber.toString()
            } else if (operation.isBlank() && !complete) {
                leftNumber = leftNumber * 10 + btnNum
                displayText.value = leftNumber.toString()
            } else {
                if (displayText.value == "0") {
                    displayText.value = btnNum.toString()
                } else {
                    displayText.value += btnNum.toString()
                }
            }
        }
    }

    fun operationPress(op: String) {
        if (!complete) {
            operation = op
        }
    }

    fun equalsPress() {
        complete = true
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
                    CalcRow(display = displayText, startNum = i, numButtons = 3, onNumberClick = ::numberPress)
                }
                Row(
                    modifier = Modifier.padding(0.dp)
                ) {
                    CalcNumericButton(number = 0, onClick = { numberPress(0) })
                    CalcEqualsButton(onClick = { equalsPress() })
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                CalcOperationButton("+", onClick = { operationPress("+") })
                CalcOperationButton("-", onClick = { operationPress("-") })
                CalcOperationButton("*", onClick = { operationPress("*") })
                CalcOperationButton("/", onClick = { operationPress("/") })
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
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int, onNumberClick: (Int) -> Unit) {
    val endNum = startNum + numButtons
    Row(
        modifier = Modifier.padding(0.dp)
    ) {
        for (i in startNum until endNum) {
            CalcNumericButton(number = i, onClick = { onNumberClick(i) })
        }
    }
}

@Composable
fun CalcOperationColumn(onOperationClick: (String) -> Unit) {
    Column {
        CalcOperationRow(onOperationClick = onOperationClick)
    }
}

@Composable
fun CalcOperationRow(onOperationClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalcOperationButton(operation = "+", onClick = { onOperationClick("+") })
        CalcOperationButton(operation = "-", onClick = { onOperationClick("-") })
        CalcOperationButton(operation = "*", onClick = { onOperationClick("*") })
        CalcOperationButton(operation = "/", onClick = { onOperationClick("/") })
    }
}

@Composable
fun CalcNumericButton(number: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(4.dp)) {
        Text(text = "=")
    }
}
