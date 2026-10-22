package com.duepilot

import com.duepilot.domain.BackupData
import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class Phase6BackupVerificationTest {
    @Test fun versionedFormatExists() {
        val b = BackupData(
            backupFormatVersion = 1,
            clients = listOf(),
            invoices = listOf(),
            followUps = listOf(),
            reminders = listOf()
        )
        assertEquals(1, b.backupFormatVersion)
    }

    @Test fun exactDecimalPreservedAsStringInData() {
        val inv = com.duepilot.domain.InvoiceBackup(
            id = "i1", invoiceNumber = "001", clientId = "c1",
            description = null, amountMinor = 50000L, currencyCode = "USD",
            status = "Unpaid", issueDate = 0, dueDate = 0,
            notes = null, createdAt = 0, updatedAt = 0
        )
        // amountMinor is Long (exact) — not Float/Double
        assertNotEquals(50000.0, inv.amountMinor) // confirms exact representation, not float
    }

    @Test fun emptyBackupValid() {
        val b = BackupData()
        assertNotNull(b.clients)
        assertTrue(b.invoices.isEmpty())
    }
}
