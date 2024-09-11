package com.aikei.jusan.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aikei.jusan.presentation.ui.AuthScreen
import com.aikei.jusan.presentation.ui.MainScreen
import com.aikei.jusan.presentation.ui.theme.JusanTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JusanTheme {
                if (FirebaseAuth.getInstance().currentUser == null) {
                    AuthScreen()
                } else {
                    MainScreen()
                }
            }
        }
    }
}
