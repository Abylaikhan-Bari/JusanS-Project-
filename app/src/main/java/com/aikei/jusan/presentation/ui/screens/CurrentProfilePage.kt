package com.aikei.jusan.presentation.ui.screens

import androidx.compose.runtime.Composable
import com.aikei.jusan.domain.viewmodel.CurrentProfileViewModel

@Composable
fun CurrentProfilePage(viewModel: CurrentProfileViewModel) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    Scaffold(
//        topBar = { TopAppBar(title = "Profile") },
//        bottomBar = { BottomNavigation() }
//    ) { padding ->
//        Column(modifier = Modifier.padding(padding)) {
//            // User profile information
//            Text(text = "Name: ${uiState.user.name}", modifier = Modifier.padding(bottom = 16.dp))
//            Text(text = "Email: ${uiState.user.email}", modifier = Modifier.padding(bottom = 16.dp))
//            // ... (other user profile information)
//
//            // Edit profile button
//            Button(
//                onClick = { /* Navigate to edit profile screen */ },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Edit Profile")
//            }
//        }
//    }
}
