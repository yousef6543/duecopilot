package com.duepilot

import com.duepilot.domain.PaymentInsights
import java.math.BigDecimal
import org.junit.Assert.*
import org.junit.Test

class PaymentInsightsTest {
    @Test
    fun currencySeparated() {
        val out = mapOf("USD" to BigDecimal("100.00"), "EUR" to BigDecimal("50.00"))
        val insights = PaymentInsights(out, mapOf(), mapOf(), mapOf())
        assertEquals(BigDecimal("100.00"), insights.outstandingByCurrency["USD"])
        assertNotEquals(BigDecimal("150.00"), insights.outstandingByCurrency.values.sumOf { it }) // wrong; separate not combined
    }

    @Test
    fun emptyDataHandled() {
        val i = PaymentInsights(mapOf(), mapOf(), mapOf(), mapOf())
        assertTrue(i.isEmpty())
    }
}
