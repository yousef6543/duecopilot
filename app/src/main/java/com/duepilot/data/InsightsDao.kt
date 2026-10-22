package com.duepilot.data

import androidx.room.Dao
import androidx.room.Query
import com.duepilot.data.entity.InvoiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InsightsDao {
    @Query("SELECT * FROM invoices WHERE status = 'Unpaid' OR status = 'Overdue'")
    fun unpaidOrOverdue(): Flow<List<InvoiceEntity>>

    @Query("SELECT * FROM invoices WHERE status = 'Paid'")
    fun paidInvoices(): Flow<List<InvoiceEntity>>

    @Query("SELECT * FROM invoices")
    fun allInvoices(): Flow<List<InvoiceEntity>>
}
