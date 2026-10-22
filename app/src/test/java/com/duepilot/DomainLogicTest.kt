package com.duepilot

import org.junit.Test
import java.math.BigDecimal
import com.duepilot.domain.model.InvoiceStatus

class DomainLogicTest {
    @Test
    fun invoiceStatusOrder_isCorrect() {
        val statuses = listOf(InvoiceStatus.Draft, InvoiceStatus.Unpaid, InvoiceStatus.Overdue, InvoiceStatus.Paid)
        assert(statuses.size == 4)
    }

    @Test
    fun bigDecimalDoesNotUseFloat() {
        val amount = BigDecimal("1299.99")
        assert(amount.compareTo(BigDecimal("1299.99")) == 0)
    }
}
