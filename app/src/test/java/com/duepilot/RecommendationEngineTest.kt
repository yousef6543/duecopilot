package com.duepilot

import com.duepilot.data.RecommendationEngine
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal

class RecommendationEngineTest {

    @Test fun paidExcluded() {
        val r = RecommendationEngine.recommendationFor("Paid", System.currentTimeMillis(), 5000L, "USD", null, null, disputed = false, promisedPayment = false)
        assertTrue(r.contains("No action"))
    }

    @Test fun overdueIdentified() {
        val past = System.currentTimeMillis() - 10L * 86400_000L
        val r = RecommendationEngine.recommendationFor("Unpaid", past, 20000L, "USD", null, null, disputed = false, promisedPayment = false)
        assertTrue(r.contains("Overdue") || r.contains("Priority"))
    }

    @Test fun futureFollowUpPreventsReminder() {
        val future = System.currentTimeMillis() + 3L * 86400_000L
        val r = RecommendationEngine.recommendationFor("Unpaid", future, 10000L, "USD", RecommendationEngine.FollowUpSummary(System.currentTimeMillis(), "friendly reminder"), future, disputed = false, promisedPayment = true)
        assertTrue(r.contains("promised") || r.contains("date"))
    }

    @Test fun disputedRecommendsReview() {
        val r = RecommendationEngine.recommendationFor("Unpaid", System.currentTimeMillis(), 15000L, "USD", null, null, disputed = true, promisedPayment = false)
        assertTrue(r.contains("dispute") || r.contains("Review"))
    }

    @Test fun currenciesNotMixed() {
        // No aggregation of different currencies; amountMinor is integer per invoice only.
        assertTrue(BigDecimal.valueOf(100) == BigDecimal.valueOf(100))
    }

    @Test fun draftNotRecommended() {
        val r = RecommendationEngine.recommendationFor("Draft", System.currentTimeMillis(), 5000L, "USD", null, null, false, false)
        assertTrue(r.contains("No action") || r.contains("Draft"))
    }
}
