package com.sonusid.mello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sonusid.mello.presentation.homescreen.PreviewHomeScreenContent
import com.sonusid.mello.ui.theme.MelloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MelloTheme {
                PreviewHomeScreenContent()
            }
        }
    }
}
