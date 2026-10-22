package com.duepilot.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "reminders",
    foreignKeys = [
        ForeignKey(entity = InvoiceEntity::class, parentColumns = ["id"], childColumns = ["invoiceId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = ClientEntity::class, parentColumns = ["id"], childColumns = ["clientId"], onDelete = ForeignKey.SET_NULL)
    ]
)
data class ReminderEntity(
    @PrimaryKey val id: String,
    val invoiceId: String?,
    val followUpId: String?,
    val clientId: String?,
    val scheduledMs: Long,
    val enabled: Boolean,
    val note: String?,
    val createdAt: Long,
    val updatedAt: Long
)
