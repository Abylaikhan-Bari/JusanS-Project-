package com.aikei.jusan.presentation.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aikei.jusan.domain.viewmodel.AlbumsViewModel
import com.aikei.jusan.domain.viewmodel.CurrentProfileViewModel
import com.aikei.jusan.domain.viewmodel.PostsViewModel
import com.aikei.jusan.domain.viewmodel.UsersViewModel
import com.aikei.jusan.presentation.ui.screens.albums.AlbumsPage
import com.aikei.jusan.presentation.ui.screens.profile.CurrentProfilePage
import com.aikei.jusan.presentation.ui.screens.posts.PostsPage
import com.aikei.jusan.presentation.ui.screens.users.UsersPage
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.presentation.ui.screens.posts.PostDetailsPage

object NavGraph {
    object PostsPage {
        const val route = "posts_page"
    }

    object PostDetailsPage {
        const val route = "post_details"
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
            val postsViewModel: PostsViewModel = hiltViewModel()
            PostsPage(viewModel = postsViewModel, navController = navController)
        }
        composable("${NavGraph.PostDetailsPage.route}/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
            val postsViewModel: PostsViewModel = hiltViewModel()
            PostDetailsPage(postId = postId, viewModel = postsViewModel)
        }

        composable(NavGraph.AlbumsPage.route) {
            onNavigate(NavGraph.AlbumsPage.route)
            val albumsViewModel: AlbumsViewModel = hiltViewModel()
            AlbumsPage(viewModel = albumsViewModel)
        }
        composable(NavGraph.UsersPage.route) {
            onNavigate(NavGraph.UsersPage.route)
            val usersViewModel: UsersViewModel = hiltViewModel()
            UsersPage(viewModel = usersViewModel)
        }
        composable(NavGraph.CurrentProfilePage.route) {
            onNavigate(NavGraph.CurrentProfilePage.route)
            val currentProfileViewModel: CurrentProfileViewModel = hiltViewModel()
            CurrentProfilePage(viewModel = currentProfileViewModel, navController)
        }
    }
}
