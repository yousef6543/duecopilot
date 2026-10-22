package com.duepilot.domain

/**
 * Scheduling mechanism chosen for Phase 4.
 *
 * WorkManager (Periodic/OneTime) is used for reminders that can tolerate OS scheduling delays.
 * For user-selected exact clock times (e.g., "2pm tomorrow"), AlarmManager with
 * AlarmManager.setExactAndAllowWhileIdle is evaluated; if unavailable (exact-alarm
 * permission not granted, Android 13+ restrictions), fallback to WorkManager with
 * notification at closest practical window, documented in UI.
 *
 * Timezone: system default ZoneId, persistent via Long epoch ms.
 * Reboot: WorkManager restores automatically; AlarmManager requires RESTORE after reboot.
 * This project uses Room + WorkManager to avoid requiring SCHEDULE_EXACT_ALARM.
 */
class ReminderScheduler(private val workEnqueuer: (workName: String, delayMs: Long) -> Unit) {
    fun schedule(invoiceId: String, scheduledMs: Long) {
        val delay = scheduledMs - System.currentTimeMillis()
        if (delay > 0) workEnqueuer("rem_$invoiceId", delay)
    }
    fun cancel(invoiceId: String) { /* WorkManager cancellation by tag */ }
}
