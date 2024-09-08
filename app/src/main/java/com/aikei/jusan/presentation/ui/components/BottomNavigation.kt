package com.aikei.jusan.presentation.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import com.aikei.jusan.R
import com.aikei.jusan.presentation.ui.navigation.NavGraph

@Composable
fun BottomNavigation(navController: NavHostController) {
    BottomAppBar {
        NavigationBarItem(
            icon = { Icon(ImageVector.vectorResource(R.drawable.ic_home), contentDescription = null) },
            label = { Text("Posts") },
            selected = navController.currentDestination?.route == NavGraph.PostsPage.route,
            onClick = {
                navController.navigate(NavGraph.PostsPage.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(ImageVector.vectorResource(R.drawable.ic_album), contentDescription = null) },
            label = { Text("Albums") },
            selected = navController.currentDestination?.route == NavGraph.AlbumsPage.route,
            onClick = {
                navController.navigate(NavGraph.AlbumsPage.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(ImageVector.vectorResource(R.drawable.ic_user), contentDescription = null) },
            label = { Text("Users") },
            selected = navController.currentDestination?.route == NavGraph.UsersPage.route,
            onClick = {
                navController.navigate(NavGraph.UsersPage.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(ImageVector.vectorResource(R.drawable.ic_profile), contentDescription = null) },
            label = { Text("Profile") },
            selected = navController.currentDestination?.route == NavGraph.CurrentProfilePage.route,
            onClick = {
                navController.navigate(NavGraph.CurrentProfilePage.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }
}
