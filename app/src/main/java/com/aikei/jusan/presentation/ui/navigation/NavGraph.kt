package com.aikei.jusan.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aikei.jusan.domain.viewmodel.*
import com.aikei.jusan.presentation.ui.screens.albums.AlbumPhotosPage
import com.aikei.jusan.presentation.ui.screens.albums.AlbumsPage
import com.aikei.jusan.presentation.ui.screens.posts.PostDetailsPage
import com.aikei.jusan.presentation.ui.screens.posts.PostsPage
import com.aikei.jusan.presentation.ui.screens.profile.CurrentProfilePage
import com.aikei.jusan.presentation.ui.screens.users.UserProfilePage
import com.aikei.jusan.presentation.ui.screens.users.UsersPage
import androidx.hilt.navigation.compose.hiltViewModel

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
            val photosViewModel: PhotosViewModel = hiltViewModel()
            AlbumsPage(
                navController = navController,
                viewModel = albumsViewModel,
                photoViewModel = photosViewModel
            )
        }

        composable(NavGraph.UsersPage.route) {
            onNavigate(NavGraph.UsersPage.route)
            val usersViewModel: UsersViewModel = hiltViewModel()
            UsersPage(navController = navController, viewModel = usersViewModel)
        }

        composable("${NavGraph.UsersPage.route}/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            val usersViewModel: UsersViewModel = hiltViewModel()
            val selectedUser = usersViewModel.uiState.value.users.find { it.id == userId }
            selectedUser?.let {
                UserProfilePage(user = it)
            }
        }

        composable(NavGraph.CurrentProfilePage.route) {
            onNavigate(NavGraph.CurrentProfilePage.route)
            val currentProfileViewModel: CurrentProfileViewModel = hiltViewModel()
            CurrentProfilePage(viewModel = currentProfileViewModel, navController)
        }

        composable("${NavGraph.AlbumsPage.route}/{albumId}/{albumTitle}") { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId")?.toIntOrNull() ?: return@composable
            val albumTitle = backStackEntry.arguments?.getString("albumTitle") ?: "Album"
            val photosViewModel: PhotosViewModel = hiltViewModel()
            AlbumPhotosPage(albumId = albumId, albumTitle = albumTitle, viewModel = photosViewModel)
        }
    }
}
