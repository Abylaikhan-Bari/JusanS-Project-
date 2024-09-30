package com.aikei.jusan.presentation.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PinSetupPage(onPinSet: (String) -> Unit) {
    var pin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Set up your 4-digit PIN",
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

        // Keypad for PIN entry
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
                                        onPinSet(pin)
                                        pin = "" // Clear after setting
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
    }
}
