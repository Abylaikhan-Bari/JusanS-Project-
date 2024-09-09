package com.aikei.jusan.presentation.ui.screens.users

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aikei.jusan.data.model.User

@Composable
fun UserProfilePage(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // User Info
        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Email: ${user.email}")
                Text(text = "Full Name: ${user.name}")
                Text(text = "Phone: ${user.phone}")
                Text(text = "Website: ${user.website}")
            }
        }
        // Company Info
        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Company: ${user.company.name}")
                Text(text = "Catchphrase: ${user.company.catchPhrase}")
                Text(text = "Business Services: ${user.company.bs}")
            }
        }
        // Address Info
        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Street: ${user.address.street}")
                Text(text = "Suite: ${user.address.suite}")
                Text(text = "City: ${user.address.city}")
                Text(text = "Zip Code: ${user.address.zipcode}")
            }
        }
    }
}