package com.aikei.jusan.presentation.ui.components.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aikei.jusan.data.model.*

@Composable
fun ProfileItem(
    user: User,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        // Profile title
        Text(
            text = user.username,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        // User details card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                UserInfoRow("Email:", user.email, Color.Blue)
                UserInfoRow("Full Name:", user.name, Color.Black)
                UserInfoRow("Phone:", user.phone, Color.Red)
                UserInfoRow("Website:", user.website, Color.Blue)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // My ToDos card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("todos_page")
                }
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "My ToDos",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Company card
        CardSection(
            title = "Company",
            content = "Company Name: ${user.company.name}\nBusiness Services: ${user.company.catchPhrase}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Address card
        CardSection(
            title = "Address",
            content = "Street: ${user.address.street}\nSuite: ${user.address.suite}\nCity: ${user.address.city}\nZipcode: ${user.address.zipcode}",
            linkText = "Show On Map"
        )
    }
}

@Composable
fun UserInfoRow(label: String, value: String, textColor: Color) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Text(text = "$label ", style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium.copy(color = textColor))
    }
}

@Composable
fun CardSection(title: String, content: String, linkText: String? = null) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = content, style = MaterialTheme.typography.bodyMedium)

            if (linkText != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = linkText,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Blue)
                    )
                }
            }
        }
    }
}
