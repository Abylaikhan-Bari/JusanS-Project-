package com.aikei.jusan.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aikei.jusan.ui.components.BottomNavigation
import com.aikei.jusan.ui.navigation.NavGraph
import com.aikei.jusan.ui.navigation.NavHostContainer
import com.aikei.jusan.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()
    val currentPageTitle by mainViewModel.currentPageTitle.collectAsState()

    MainContent(
        navController = navController,
        currentPageTitle = currentPageTitle,
        onNavigate = { route ->
            mainViewModel.updateCurrentPageTitle(
                when (route) {
                    NavGraph.PostsPage.route -> "Posts"
                    NavGraph.AlbumsPage.route -> "Albums"
                    NavGraph.UsersPage.route -> "Users"
                    NavGraph.CurrentProfilePage.route -> "Profile"
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
    onNavigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentPageTitle)
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
