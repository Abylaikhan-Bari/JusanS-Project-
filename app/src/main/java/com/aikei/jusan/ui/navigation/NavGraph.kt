package com.aikei.jusan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aikei.jusan.ui.screens.*
import com.aikei.jusan.viewmodel.AlbumsViewModel
import com.aikei.jusan.viewmodel.CurrentProfileViewModel
import com.aikei.jusan.viewmodel.PostsViewModel
import com.aikei.jusan.viewmodel.UsersViewModel

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
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavGraph.PostsPage.route) {
            PostsPage(viewModel = PostsViewModel())
        }
        composable(NavGraph.AlbumsPage.route) {
            AlbumsPage(viewModel = AlbumsViewModel())
        }
        composable(NavGraph.UsersPage.route) {
            UsersPage(viewModel = UsersViewModel())
        }
        composable(NavGraph.CurrentProfilePage.route) {
            CurrentProfilePage(viewModel = CurrentProfileViewModel())
        }
    }
}
