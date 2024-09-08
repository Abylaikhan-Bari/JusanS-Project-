package com.aikei.jusan.presentation.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.aikei.jusan.presentation.ui.components.BottomNavigation
import com.aikei.jusan.presentation.ui.components.TopAppBar
import com.aikei.jusan.domain.viewmodel.UsersViewModel

@Composable
fun UsersPage(viewModel: UsersViewModel) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    Scaffold(
//        topBar = { TopAppBar(title = "Users") },
//        bottomBar = { BottomNavigation() }
//    ) { padding ->
//        LazyColumn(contentPadding = padding) {
//            items(uiState.users) { user ->
//                UserListItem(user = user)
//            }
//        }
//    }
}
