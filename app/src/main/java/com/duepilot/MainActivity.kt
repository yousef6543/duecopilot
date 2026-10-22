package com.duepilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.duepilot.ui.DashboardScreen
import com.duepilot.ui.theme.DuePilotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DuePilotTheme { DashboardScreen() } }
    }
}
