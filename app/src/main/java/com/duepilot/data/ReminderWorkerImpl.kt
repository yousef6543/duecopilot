package com.duepilot.data

import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ReminderWorkerImpl(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val invoiceId = inputData.getString("invoiceId") ?: return Result.failure()
        val remId = inputData.getString("reminderId")
        val db = DuePilotDatabase.getDatabase(appContext)
        val rem = if (remId != null) db.reminderDao().getById(remId) else null
        if (rem == null || !rem.enabled) return Result.success()
        // Notification delivery (Phase 4)
        NotificationHelper.createChannel(appContext)
        val nm = appContext.getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        if (nm.areNotificationsEnabled()) {
            val nidStr = remId ?: invoiceId
            val notification = NotificationHelper.buildNotification(
                appContext,
                invoiceId,
                nidStr,
                "DuePilot: Invoice $invoiceId",
                "Follow-up reminder due for invoice $invoiceId (${remId ?: ""})"
            )
            nm.notify(nidStr.hashCode(), notification)
        }
        return Result.success()
    }
}
