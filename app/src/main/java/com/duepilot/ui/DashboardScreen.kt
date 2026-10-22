package com.duepilot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.duepilot.R

@Composable
fun DashboardScreen() {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("DuePilot") }) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("Dashboard — Phase 1 Foundation", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(8.dp))
                    Text("Due today · Overdue · Recently followed up · Suggested actions")
                    Spacer(Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        MetricCard("Due Today", "0")
                        MetricCard("Overdue", "0")
                        MetricCard("Followed Up", "0")
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("Welcome to DuePilot", style = MaterialTheme.typography.titleMedium)
                    Text("Track unpaid invoices, follow up with clients, and stay professional. This is the Phase 1 foundation.", modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}

@Composable
fun MetricCard(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.headlineMedium)
        Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
