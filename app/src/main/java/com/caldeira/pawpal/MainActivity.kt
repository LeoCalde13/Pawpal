package com.caldeira.pawpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.caldeira.pawpal.ui.screens.PawpalNavHost
import com.caldeira.pawpal.ui.theme.PawpalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PawpalTheme {
                val navController = rememberNavController()
                PawpalNavHost(navController = navController)
            }
        }
    }
}