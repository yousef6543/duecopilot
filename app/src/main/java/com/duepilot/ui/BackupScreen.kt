package com.duepilot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Phase 6 — Backup / Restore screen.
 * Material 3, light/dark, mobile-friendly, clear confirmations.
 */
@Composable
fun BackupScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Backup & Restore") }, navigationIcon = { TextButton(onClick = onBack) { Text("Back") } }) }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("DuePilot Backup v1", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Text("Exports clients, invoices, follow-ups, reminders (JSON). Amounts stored as exact decimal strings by currency.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = { /* Export via Android document picker + share */ }) { Text("Export Backup") }
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(onClick = { /* Import/Restore with validation */ }) { Text("Import / Restore") }
                    Spacer(Modifier.height(8.dp))
                    Text("Restore strategy: validate full file before changing DB; merge requires explicit confirmation; never silently overwrite.", style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(Modifier.height(12.dp))
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("Status", style = MaterialTheme.typography.titleMedium)
                    Text("Offline-first. No cloud, no paid API. Choose file via Android picker.", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
