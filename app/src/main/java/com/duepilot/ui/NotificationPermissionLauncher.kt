package com.duepilot.ui

import android.Manifest
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.activity.compose.rememberLauncherForActivityResult

@Composable
fun NotificationPermissionLauncher(onResult: (Boolean) -> Unit) {
    // Phase 4: permission launcher for POST_NOTIFICATIONS on Android 13+
    val launcher = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        remember {
            androidx.activity.compose.rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { granted: Boolean -> onResult(granted) }
        }
    } else null
    // UI integration deferred to settings screen; launcher registered.
}
