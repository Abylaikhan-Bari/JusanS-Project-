package com.aikei.jusan.presentation.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PinPage(onPinEnter: (String) -> Unit) {
    var pin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unlock with pin code",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dots to represent entered PIN digits
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(4) { index ->
                Text(
                    text = if (index < pin.length) "●" else "○",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 40.sp,
                        color = if (index < pin.length) Color(0xFF9C27B0) else Color.LightGray
                    ),
                    modifier = Modifier.padding(8.dp)
                )

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Keypad
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (row in listOf("1 2 3", "4 5 6", "7 8 9", " 0 ")) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.trim().split(" ").forEach { number ->
                        if (number.isNotEmpty()) {
                            KeypadButton(
                                number = number,
                                onClick = {
                                    if (pin.length < 4) pin += number
                                    if (pin.length == 4) {
                                        onPinEnter(pin)
                                        pin = "" // Clear after submission
                                    }
                                }
                            )
                        } else {
                            Spacer(Modifier.size(64.dp)) // Empty space for layout
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Option to reset PIN or recover
        Text(
            text = "Forgot your pin code?",
            color = Color(0xFF9C27B0),
            modifier = Modifier.clickable { /* Handle forgot PIN */ },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun KeypadButton(number: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(64.dp)
            .background(Color.LightGray, shape = MaterialTheme.shapes.small)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = number,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
    }
}
