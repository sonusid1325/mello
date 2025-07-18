package com.sonusid.mello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.sonusid.mello.presentation.navigation.MelloNavGraph
import com.sonusid.mello.ui.theme.MelloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MelloTheme {
                val navController = rememberNavController()
                MelloNavGraph(navController = navController)
            }
        }
    }
}
