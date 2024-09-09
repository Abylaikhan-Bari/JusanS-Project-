package com.aikei.jusan.presentation.ui.screens.todos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aikei.jusan.domain.viewmodel.ToDoViewModel
import com.aikei.jusan.presentation.ui.components.todo.ToDoItem
import com.aikei.jusan.presentation.ui.screens.posts.ErrorMessage
import com.aikei.jusan.presentation.ui.screens.posts.LoadingIndicator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToDosPage(
    toDoViewModel: ToDoViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by toDoViewModel.uiState.collectAsState()

    Scaffold {
        when {
            uiState.isLoading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }
            uiState.error != null -> {
                ErrorMessage(message = uiState.error!!, modifier = Modifier.fillMaxSize())
            }
            uiState.todos.isNotEmpty() -> {
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(uiState.todos) { todo ->
                        ToDoItem(todo = todo)
                    }
                }
            }
        }
    }
}

