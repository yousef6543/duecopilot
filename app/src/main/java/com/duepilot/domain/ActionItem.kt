package com.duepilot.domain

/**
 * Action Center — reuses RecommendationEngine rules.
 * Deduplicated; explains why each item appears.
 */
data class ActionItem(
    val reason: String,
    val invoiceId: String?,
    val clientId: String?,
    val actionType: String,
    val explanation: String
)
