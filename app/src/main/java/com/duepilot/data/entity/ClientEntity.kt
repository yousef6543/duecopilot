package com.duepilot.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class ClientEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String?,
    val company: String?,
    val notes: String?,
    val createdAt: Long
)
