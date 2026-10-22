package com.duepilot.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "follow_ups",
    foreignKeys = [
        ForeignKey(entity = InvoiceEntity::class, parentColumns = ["id"], childColumns = ["invoiceId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class FollowUpEntity(
    @PrimaryKey val id: String,
    val invoiceId: String,
    val note: String,
    val date: Long
)
