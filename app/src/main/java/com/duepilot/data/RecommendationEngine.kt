package com.duepilot.data

import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Deterministic recommendation rules — local only, no AI/network.
 */
object RecommendationEngine {

    fun recommendationFor(
        invoiceStatus: String,
        dueDateMs: Long,
        amountMinor: Long,
        currencyCode: String,
        lastFollowUp: FollowUpSummary?,
        nextFollowUpMs: Long?,
        disputed: Boolean,
        promisedPayment: Boolean
    ): String {
        if (invoiceStatus == "Paid" || invoiceStatus == "Draft") return "No action needed."
        if (disputed) return "Review and resolve dispute before sending routine reminders."
        if (promisedPayment && nextFollowUpMs != null && nextFollowUpMs > nowMs()) {
            return "Client promised payment. Follow up on the promised date (${nextDateLabel(nextFollowUpMs)})."
        }
        val overdueMs = dueMs(dueDateMs)
        val isOverdue = overdueMs > 0
        if (isOverdue && (lastFollowUp == null || lastFollowUp.dateMs < nowMs() - 7L * 86400_000L)) {
            return "Overdue ($overdueMs days). Priority: review invoice and contact client."
        }
        if (lastFollowUp != null && lastFollowUp.type == "awaiting response") {
            return "Awaiting client response. Do not send duplicate reminder until response period passes."
        }
        if (isOverdue) return "Overdue. Recommend a firm but respectful reminder."
        val dueInDays = (dueDateMs - nowMs()) / 86400_000L
        return if (dueInDays <= 3) "Due very soon ($dueInDays days). Friendly reminder recommended." else "Due in $dueInDays days. Monitor closely."
    }

    fun labelForStatus(status: String, dueMs: Long): String {
        if (status == "Paid") return "Paid"
        if (status == "Draft") return "Draft"
        if (dueMs < 0) return "Overdue (${(-dueMs / 86400_000L)}d)"
        if (dueMs == 0L) return "Due today"
        return "Due in ${dueMs / 86400_000L}d"
    }

    private fun nowMs() = System.currentTimeMillis()
    private fun dueMs(dueMs: Long) = (dueMs - nowMs()) / 86400_000L
    private fun nextDateLabel(ms: Long) = ZonedDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneId.systemDefault()).toLocalDate().toString()

    data class FollowUpSummary(val dateMs: Long, val type: String)
}
