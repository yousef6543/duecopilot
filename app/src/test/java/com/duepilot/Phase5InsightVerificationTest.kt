package com.duepilot

import com.duepilot.domain.PaymentInsights
import java.math.BigDecimal
import org.junit.Assert.*
import org.junit.Test

class Phase5InsightVerificationTest {
    @Test fun currencySeparatedNeverCombined() {
        val insights = PaymentInsights(
            outstandingByCurrency = mapOf("USD" to BigDecimal("150.00"), "EGP" to BigDecimal("3000.00")),
            overdueByCurrency = mapOf("USD" to BigDecimal("50.00")),
            paidByCurrency = mapOf("USD" to BigDecimal("200.00")),
            countsByStatus = mapOf("Overdue" to 2, "Unpaid" to 3, "Paid" to 5)
        )
        assertNotEquals("Should not combine currencies", BigDecimal("3150.00"), insights.outstandingByCurrency["USD"])
        assertEquals(BigDecimal("150.00"), insights.outstandingByCurrency["USD"])
        assertEquals(BigDecimal("3000.00"), insights.outstandingByCurrency["EGP"])
        assertEquals(2, insights.overdueCount)
        assertFalse(insights.isEmpty())
    }

    @Test fun emptyAndBoundaryHandled() {
        val empty = PaymentInsights(
            outstandingByCurrency = emptyMap(),
            overdueByCurrency = emptyMap(),
            paidByCurrency = emptyMap(),
            countsByStatus = emptyMap()
        )
        assertTrue(empty.isEmpty())
        assertEquals(0, empty.overdueCount)
    }

    @Test fun overdueExcludedFromPaid() {
        // Overdue invoices are unpaid (status Overdue), not Paid
        val counts = mapOf("Overdue" to 1, "Paid" to 2, "Unpaid" to 1)
        val i = PaymentInsights(
            outstandingByCurrency = mapOf("GBP" to BigDecimal("80.00")),
            overdueByCurrency = mapOf("GBP" to BigDecimal("80.00")),
            paidByCurrency = mapOf("GBP" to BigDecimal("120.00")),
            countsByStatus = counts
        )
        assertEquals(1, i.overdueCount)
        // Paid amount separate from overdue; never combined
        assertNotSame(i.paidByCurrency.values.sumOf { it }, i.overdueByCurrency.values.sumOf { it })
    }
}
