package com.duepilot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReminderScreen(
    invoiceId: String?,
    onBack: () -> Unit,
    onSave: (dateMs: Long) -> Unit
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Reminder") }, navigationIcon = { TextButton(onClick = onBack) { Text("Back") } }) }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp).padding(bottom = 16.dp)) {
            Text("Schedule a follow-up reminder", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("Invoice: ${invoiceId ?: "—"}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(8.dp))
                    Text("Select a future time. WorkManager delivers approximately at that time.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(12.dp))
                    // Simplified: user picks date/time via basic controls (full picker deferred to keep build stable)
                    OutlinedButton(onClick = { onSave(System.currentTimeMillis() + 86400_000L) }) { Text("Schedule +1 day") }
                    Spacer(Modifier.height(8.dp))
                    TextButton(onClick = onBack) { Text("Cancel") }
                }
            }
        }
    }
}
