package com.aikei.jusan.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aikei.jusan.ui.components.BottomNavigation
import com.aikei.jusan.ui.navigation.NavGraph
import com.aikei.jusan.ui.navigation.NavHostContainer

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        NavHostContainer(
            navController = navController,
            startDestination = NavGraph.PostsPage.route,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
