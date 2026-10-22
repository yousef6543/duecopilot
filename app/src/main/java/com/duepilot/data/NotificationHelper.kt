package com.duepilot.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationHelper {
    const val CHANNEL_ID = "duepilot_reminders"
    const val CHANNEL_NAME = "DuePilot Reminders"

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val ch = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            ch.description = "Scheduled invoice follow-up reminders"
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(ch)
        }
    }

    fun buildNotification(context: Context, invoiceId: String, reminderId: String?, title: String, message: String): android.app.Notification {
        // Deep link PendingIntent handled via notification click; for Phase 4, create a basic intent opening app
        val intent = android.content.Intent(context, Class.forName("com.duepilot.MainActivity")).apply {
            flags = android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra("invoiceId", invoiceId)
            putExtra("reminderId", reminderId)
        }
        val flags = android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        val pending = android.app.PendingIntent.getActivity(context, invoiceId.hashCode(), intent, flags)
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(pending)
            .build()
    }
}
