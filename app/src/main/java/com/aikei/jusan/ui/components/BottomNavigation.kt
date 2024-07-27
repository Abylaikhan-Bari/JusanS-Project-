package com.aikei.jusan.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.aikei.jusan.R
import com.aikei.jusan.ui.navigation.NavGraph

@Composable
fun BottomNavigation(navController: NavHostController) {
    val currentDestination: NavDestination? = navController.currentDestination

    BottomAppBar {
        NavigationBarItem(
            icon = { Icon(ImageVector.vectorResource(R.drawable.ic_home), contentDescription = null) },
            label = { Text("Posts") },
            selected = currentDestination?.hierarchy?.any { it.route == NavGraph.PostsPage.route } == true,
            onClick = {
                navController.navigate(NavGraph.PostsPage.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(ImageVector.vectorResource(R.drawable.ic_album), contentDescription = null) },
            label = { Text("Albums") },
            selected = currentDestination?.hierarchy?.any { it.route == NavGraph.AlbumsPage.route } == true,
            onClick = {
                navController.navigate(NavGraph.AlbumsPage.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(ImageVector.vectorResource(R.drawable.ic_user), contentDescription = null) },
            label = { Text("Users") },
            selected = currentDestination?.hierarchy?.any { it.route == NavGraph.UsersPage.route } == true,
            onClick = {
                navController.navigate(NavGraph.UsersPage.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(ImageVector.vectorResource(R.drawable.ic_profile), contentDescription = null) },
            label = { Text("Profile") },
            selected = currentDestination?.hierarchy?.any { it.route == NavGraph.CurrentProfilePage.route } == true,
            onClick = {
                navController.navigate(NavGraph.CurrentProfilePage.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
