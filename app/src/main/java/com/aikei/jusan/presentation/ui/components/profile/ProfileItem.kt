package com.aikei.jusan.presentation.ui.components.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aikei.jusan.data.model.Address
import com.aikei.jusan.data.model.Company
import com.aikei.jusan.data.model.Geo
import com.aikei.jusan.data.model.Todo
import com.aikei.jusan.data.model.User
import com.aikei.jusan.presentation.ui.components.todo.ToDoItem
import com.aikei.jusan.presentation.ui.navigation.NavGraph

@Composable
fun ProfileItem(
    user: User,
    todos: List<Todo>,
    navController: NavHostController, // Add navController as a parameter
    modifier: Modifier = Modifier
)  {
    var showTodos by remember { mutableStateOf(false) }
    Column(modifier = modifier.padding(16.dp)) {
        // User details card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Email row
                Row {
                    Text(
                        text = "Email: ",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Blue,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                // Full Name row
                Row {
                    Text(
                        text = "Full Name: ",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = user.name, // Updated to use name
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.Black
                    )
                }
                // Phone row
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
                // Website row
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

        // My ToDos card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // Trigger navigation to the ToDosPage
                    navController.navigate(NavGraph.ToDosPage.route)
                }
                .padding(8.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Text(text = "My ToDos", style = MaterialTheme.typography.titleMedium, color = Color.Red)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Show the ToDos list if toggled
        if (showTodos) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Ensures it gets proper height
            ) {
                items(todos) { todo ->
                    ToDoItem(todo = todo)
                }
            }
        }


    }

    Spacer(modifier = Modifier.height(16.dp))

    // Company details (if available)
    if (user.company.name.isNotEmpty() || user.company.catchPhrase.isNotEmpty()) {
        Text(text = "Company")

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Company name: ${user.company.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Business services: ${user.company.catchPhrase}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

    // Address details (if available)
    if (user.address.street.isNotEmpty() || user.address.suite.isNotEmpty() || user.address.city.isNotEmpty() || user.address.zipcode.isNotEmpty()) {
        Text(text = "Address")

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Street: ${user.address.street}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Suite: ${user.address.suite}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "City: ${user.address.city}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Zipcode: ${user.address.zipcode}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Show on map",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Blue
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}