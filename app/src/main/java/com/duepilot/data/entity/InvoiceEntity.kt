package com.duepilot.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "invoices",
    foreignKeys = [
        ForeignKey(entity = ClientEntity::class, parentColumns = ["id"], childColumns = ["clientId"], onDelete = ForeignKey.RESTRICT)
    ]
)
data class InvoiceEntity(
    @PrimaryKey val id: String,
    val invoiceNumber: String,
    val clientId: String,
    val description: String?,
    val amountMinor: Long, // exact: minor units (e.g., cents)
    val currencyCode: String,
    val status: String, // Draft, Unpaid, Overdue, Paid
    val issueDate: Long,
    val dueDate: Long,
    val notes: String?,
    val createdAt: Long,
    val updatedAt: Long
)
