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
            PostsPage(viewModel = PostsViewModel())
        }
        composable(NavGraph.AlbumsPage.route) {
            onNavigate(NavGraph.AlbumsPage.route)
            AlbumsPage(viewModel = AlbumsViewModel())
        }
        composable(NavGraph.UsersPage.route) {
            onNavigate(NavGraph.UsersPage.route)
            UsersPage(viewModel = UsersViewModel())
        }
        composable(NavGraph.CurrentProfilePage.route) {
            onNavigate(NavGraph.CurrentProfilePage.route)
            CurrentProfilePage(viewModel = CurrentProfileViewModel())
        }
    }
}
