package com.duepilot.data

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

/**
 * Uses WorkManager for reliable scheduling without requiring exact-alarm permission.
 * For exact-time reminders beyond OS tolerances, document limitation and ask user.
 */
object ReminderWorker {
    const val TAG = "duepilot_reminder"

    fun schedule(context: Context, invoiceId: String, reminderId: String?, scheduledMs: Long) {
        WorkManager.getInstance(context).cancelUniqueWork("rem_$invoiceId")
        val delayMs = scheduledMs - System.currentTimeMillis()
        if (delayMs <= 0) return
        val work = OneTimeWorkRequestBuilder<ReminderWorkerImpl>()
            .setInitialDelay(delayMs, TimeUnit.MILLISECONDS)
            .addTag(TAG)
            .setInputData(
                workDataOf("invoiceId" to invoiceId, "scheduledMs" to scheduledMs, "reminderId" to (reminderId ?: "rem_$invoiceId"))
            )
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork("rem_$invoiceId", ExistingWorkPolicy.REPLACE, work)
    }

    fun cancel(context: Context, invoiceId: String) {
        WorkManager.getInstance(context).cancelUniqueWork("rem_$invoiceId")
    }
}
