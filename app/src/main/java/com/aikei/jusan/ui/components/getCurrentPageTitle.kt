package com.aikei.jusan.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aikei.jusan.ui.navigation.NavGraph

@Composable
fun getCurrentPageTitle(navController: NavHostController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    return when (route) {
        NavGraph.PostsPage.route -> "Posts"
        NavGraph.AlbumsPage.route -> "Albums"
        NavGraph.UsersPage.route -> "Users"
        NavGraph.CurrentProfilePage.route -> "Profile"
        else -> "App"
    }
}