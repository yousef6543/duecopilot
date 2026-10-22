package com.duepilot.domain

import java.math.BigDecimal

/**
 * Phase 6 — Backup / Restore format v1.
 * JSON-backed, versioned; amounts as strings (BigDecimal exact).
 */
data class BackupData(
    val backupFormatVersion: Int = 1,
    val exportedAtMs: Long = 0,
    val clients: List<ClientBackup>,
    val invoices: List<InvoiceBackup>,
    val followUps: List<FollowUpBackup>,
    val reminders: List<ReminderBackup>
)

data class ClientBackup(
    val id: String, val name: String, val email: String?, val company: String?, val notes: String?, val createdAt: Long
)

data class InvoiceBackup(
    val id: String, val invoiceNumber: String, val clientId: String, val description: String?, val amountMinor: Long, val currencyCode: String, val status: String, val issueDate: Long, val dueDate: Long, val notes: String?, val createdAt: Long, val updatedAt: Long
)

data class FollowUpBackup(
    val id: String, val invoiceId: String?, val clientId: String?, val timestampMs: Long, val method: String, val type: String, val note: String?, val outcome: String?, val nextFollowUpMs: Long?, val createdAt: Long
)

data class ReminderBackup(
    val id: String, val invoiceId: String?, val followUpId: String?, val clientId: String?, val scheduledMs: Long, val enabled: Boolean, val note: String?, val createdAt: Long, val updatedAt: Long
)
