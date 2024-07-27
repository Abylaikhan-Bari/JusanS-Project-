package com.aikei.jusan.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aikei.jusan.ui.components.BottomNavigation
import com.aikei.jusan.ui.components.getCurrentPageTitle
import com.aikei.jusan.ui.navigation.NavGraph
import com.aikei.jusan.ui.navigation.NavHostContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = getCurrentPageTitle(navController))
                }
            )
        },
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
