package com.aikei.jusan.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aikei.jusan.domain.viewmodel.AlbumsViewModel
import com.aikei.jusan.domain.viewmodel.CurrentProfileViewModel
import com.aikei.jusan.domain.viewmodel.PostsViewModel
import com.aikei.jusan.domain.viewmodel.UsersViewModel
import com.aikei.jusan.presentation.ui.screens.AlbumsPage
import com.aikei.jusan.presentation.ui.screens.CurrentProfilePage
import com.aikei.jusan.presentation.ui.screens.PostsPage
import com.aikei.jusan.presentation.ui.screens.UsersPage
import androidx.hilt.navigation.compose.hiltViewModel

object NavGraph {
    object PostsPage {
        const val route = "posts_page"
    }

    object AlbumsPage {
        const val route = "albums_page"
    }

    object UsersPage {
        const val route = "users_page"
    }

    object CurrentProfilePage {
        const val route = "current_profile_page"
    }
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavGraph.PostsPage.route) {
            onNavigate(NavGraph.PostsPage.route)
            val postsViewModel: PostsViewModel = hiltViewModel() // Use Hilt to inject ViewModel
            PostsPage(viewModel = postsViewModel)
        }
        composable(NavGraph.AlbumsPage.route) {
            onNavigate(NavGraph.AlbumsPage.route)
            val albumsViewModel: AlbumsViewModel = hiltViewModel() // Use Hilt to inject ViewModel
            AlbumsPage(viewModel = albumsViewModel)
        }
        composable(NavGraph.UsersPage.route) {
            onNavigate(NavGraph.UsersPage.route)
            val usersViewModel: UsersViewModel = hiltViewModel() // Use Hilt to inject ViewModel
            UsersPage(viewModel = usersViewModel)
        }
        composable(NavGraph.CurrentProfilePage.route) {
            onNavigate(NavGraph.CurrentProfilePage.route)
            val currentProfileViewModel: CurrentProfileViewModel = hiltViewModel() // Use Hilt to inject ViewModel
            CurrentProfilePage(viewModel = currentProfileViewModel)
        }
    }
}
