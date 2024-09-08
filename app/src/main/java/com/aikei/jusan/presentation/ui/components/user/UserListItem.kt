package com.aikei.jusan.presentation.ui.components.user

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aikei.jusan.data.model.User

@Composable
fun UserListItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Row {
                    Text(
                        text = "Full name: ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = user.fullName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
                Row {
                    Text(
                        text = "Email: ",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Blue
                    )
                }
            }
        }
    }
}