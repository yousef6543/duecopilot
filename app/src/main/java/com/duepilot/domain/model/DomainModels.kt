package com.duepilot.domain.model

import java.math.BigDecimal

enum class InvoiceStatus { Draft, Unpaid, Overdue, Paid }

sealed class Currency(val code: String) {
    object USD : Currency("USD")
    object EUR : Currency("EUR")
    object GBP : Currency("GBP")
}

data class Client(
    val id: String,
    val name: String,
    val email: String?,
    val createdAt: Long = System.currentTimeMillis()
)

data class Invoice(
    val id: String,
    val clientId: String,
    val amount: BigDecimal,
    val currency: Currency,
    val status: InvoiceStatus,
    val dueDate: Long,
    val createdAt: Long = System.currentTimeMillis()
)

data class FollowUp(
    val id: String,
    val invoiceId: String,
    val note: String,
    val date: Long = System.currentTimeMillis()
)
