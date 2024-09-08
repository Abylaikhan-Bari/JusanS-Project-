package com.aikei.jusan.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.aikei.jusan.data.model.User
//import coil.compose.rememberImagePainter

@Composable
fun ProfileItem(user: User, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Text(
                        text = "Email: ",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.Blue
                    )
                }
                Row {
                    Text(
                        text = "Full Name: ",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = user.fullName,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.Black
                    )
                }
                Row {
                    Text(
                        text = "Phone: ",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = user.phone,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.Red
                    )
                }
                Row {
                    Text(
                        text = "Website: ",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = user.website,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.Blue
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ToDos Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "My ToDos",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Company")

        Spacer(modifier = Modifier.height(8.dp))

        user.company?.let {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Company",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(text = "Address")

        Spacer(modifier = Modifier.height(8.dp))

        user.address?.let {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Address",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview() {
    ProfileItem(
        user = User(
            id = "1",
            username = "johndoe",
            fullName = "Johny Doe",
            email = "johndoe@mail.com",
            phone = "+77777777701",
            website = "www.johndoe.com",
            profilePictureUrl = "https://example.com/user1.jpg",
            company = "Doe Enterprises",
            address = "123 Main St, Springfield, USA",
            todos = listOf("Complete project report", "Buy groceries", "Schedule meeting")
        )
    )
}
