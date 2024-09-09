package com.aikei.jusan.presentation.ui.screens.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aikei.jusan.data.model.User

@Composable
fun UserProfilePage(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)  // Reduce horizontal padding
    ) {
        // Username at the top, centered
        Text(
            text = user.username,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)  // Adjust padding around text
        )

        // User Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),  // Reduce vertical padding
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Email: ", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Text(text = user.email, color = Color.Blue)
                Text(text = "Full Name: ${user.name}")
                Text(text = "Phone: ${user.phone}", color = Color.Red)
                Text(text = "Website: ${user.website}", color = Color.Blue)
            }
        }

        // Company heading aligned to the left
        Text(
            text = "Company",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(vertical = 4.dp)
        )

        // Company Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Company Name: ${user.company.name}")
                Text(text = "Business Services: ${user.company.bs}")
            }
        }

        // Address heading aligned to the left
        Text(
            text = "Address",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(vertical = 4.dp)
        )

        // Address Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Street: ${user.address.street}")
                Text(text = "Suite: ${user.address.suite}")
                Text(text = "City: ${user.address.city}")
                Text(text = "Zipcode: ${user.address.zipcode}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Show On Map",
                    color = Color.Blue,
                    modifier = Modifier.clickable { /* Handle map click */ }
                )
            }
        }
    }
}
