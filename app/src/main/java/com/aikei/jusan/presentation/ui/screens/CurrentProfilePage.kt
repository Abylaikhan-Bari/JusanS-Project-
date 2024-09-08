package com.aikei.jusan.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aikei.jusan.domain.viewmodel.CurrentProfileViewModel
import com.aikei.jusan.presentation.ui.components.ProfileItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentProfilePage(
    viewModel: CurrentProfileViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { padding ->
        when {
            uiState.isLoading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize().padding(padding))
            }
            uiState.error != null -> {
                ErrorMessage(message = uiState.error, modifier = Modifier.fillMaxSize().padding(padding))
            }
            uiState.user != null -> {
                Column(modifier = Modifier.padding(padding).fillMaxSize()) {
                    ProfileItem(user = uiState.user!!)
                }
            }
        }
    }
}
