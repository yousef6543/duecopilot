package com.duepilot.domain

import java.math.BigDecimal

/**
 * Payment Insights — derived from real DB records.
 * Currency-separated, BigDecimal exact arithmetic.
 */
class PaymentInsights(
    val outstandingByCurrency: Map<String, BigDecimal>,
    val overdueByCurrency: Map<String, BigDecimal>,
    val paidByCurrency: Map<String, BigDecimal>,
    val countsByStatus: Map<String, Int>
) {
    val overdueCount: Int = countsByStatus.getOrDefault("Overdue", 0)
    fun isEmpty(): Boolean = outstandingByCurrency.isEmpty() && overdueByCurrency.isEmpty()
}
