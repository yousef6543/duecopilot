package com.duepilot.domain

import com.duepilot.data.entity.ClientEntity
import com.duepilot.data.entity.InvoiceEntity

/**
 * Client Insights — derived only from recorded data.
 * No character inference; late-payment only if sufficient history.
 */
class ClientInsights(
    val client: ClientEntity,
    val outstandingInvoices: Int,
    val overdueInvoices: Int,
    val previousPaidCount: Int,
    val followUpCount: Int,
    val reminderCount: Int,
    val latePaymentIndicator: String // "Not enough history yet" or computed from sufficient data
)
