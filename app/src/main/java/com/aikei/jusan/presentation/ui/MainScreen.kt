package com.aikei.jusan.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aikei.jusan.presentation.ui.components.BottomNavigation
import com.aikei.jusan.presentation.ui.navigation.NavGraph
import com.aikei.jusan.presentation.ui.navigation.NavHostContainer
import com.aikei.jusan.domain.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    val currentPageTitle by mainViewModel.currentPageTitle.collectAsState()
    val currentUsername by mainViewModel.currentUsername.collectAsState()

    MainContent(
        navController = navController,
        currentPageTitle = currentPageTitle,
        currentUsername = currentUsername,
        onNavigate = { route ->
            mainViewModel.updateCurrentPageTitle(
                when (route) {
                    NavGraph.PostsPage.route -> "Posts"
                    NavGraph.AlbumsPage.route -> "Albums"
                    NavGraph.UsersPage.route -> "Users"
                    NavGraph.CurrentProfilePage.route -> currentUsername // Use username for Profile
                    else -> "App"
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    navController: NavHostController,
    currentPageTitle: String,
    currentUsername: String,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // If on Profile page, show the username centered, else show the page title
                    if (currentPageTitle == currentUsername) {
                        Text(
                            text = currentUsername,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(),  // Centers text on Profile page
                            style = MaterialTheme.typography.headlineLarge
                        )
                    } else {
                        Text(
                            text = currentPageTitle,
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        NavHostContainer(
            navController = navController,
            startDestination = NavGraph.PostsPage.route,
            modifier = Modifier.padding(innerPadding),
            onNavigate = onNavigate
        )
    }
}
