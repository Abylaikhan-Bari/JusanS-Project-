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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aikei.jusan.presentation.ui.components.common.BottomNavigation
import com.aikei.jusan.presentation.ui.navigation.NavGraph
import com.aikei.jusan.presentation.ui.navigation.NavHostContainer
import com.aikei.jusan.domain.viewmodel.MainViewModel
import kotlinx.coroutines.flow.map

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
                    NavGraph.CurrentProfilePage.route -> currentUsername
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
    // Check if you're on a post details page
    val isPostDetailsPage by remember {
        navController.currentBackStackEntryFlow.map { backStackEntry ->
            backStackEntry.destination.route?.startsWith(NavGraph.PostDetailsPage.route) == true
        }
    }.collectAsState(initial = false)

    // Check if you're on an album photos page
    val isAlbumPhotosPage by remember {
        navController.currentBackStackEntryFlow.map { backStackEntry ->
            backStackEntry.destination.route?.startsWith("${NavGraph.AlbumsPage.route}/") == true
        }
    }.collectAsState(initial = false)

    // Check if you're on a user details page (to be added)
    val isUserDetailsPage by remember {
        navController.currentBackStackEntryFlow.map { backStackEntry ->
            backStackEntry.destination.route?.startsWith(NavGraph.UsersPage.route) == true
        }
    }.collectAsState(initial = false)

    // Check if you're on a to-do details page (to be added)
//    val isToDoDetailsPage by remember {
//        navController.currentBackStackEntryFlow.map { backStackEntry ->
//            backStackEntry.destination.route?.startsWith(NavGraph.ToDosPage.route) == true
//        }
//    }.collectAsState(initial = false)

    // Hide the top bar if any of the specific pages are active
    val hideTopBar = isPostDetailsPage || isAlbumPhotosPage || isUserDetailsPage

    Scaffold(
        topBar = {
            if (!hideTopBar) {
                TopAppBar(
                    title = {
                        if (currentPageTitle == currentUsername) {
                            Text(
                                text = currentUsername,
                                modifier = Modifier.fillMaxWidth().wrapContentSize(),
                                style = MaterialTheme.typography.headlineLarge
                            )
                        } else {
                            Text(
                                text = currentPageTitle,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                )
            }
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