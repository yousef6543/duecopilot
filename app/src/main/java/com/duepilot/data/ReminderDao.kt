package com.duepilot.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.duepilot.data.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(r: ReminderEntity)

    @Update
    suspend fun update(r: ReminderEntity)

    @Delete
    suspend fun delete(r: ReminderEntity)

    @Query("SELECT * FROM reminders WHERE invoiceId = :invId AND enabled = 1 ORDER BY scheduledMs ASC")
    fun activeForInvoice(invId: String): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE scheduledMs <= :now AND enabled = 1")
    suspend fun dueBefore(now: Long): List<ReminderEntity>

    @Query("SELECT COUNT(*) FROM reminders WHERE invoiceId = :invId AND scheduledMs = :ms")
    suspend fun countSameSlot(invId: String, ms: Long): Int

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getById(id: String): ReminderEntity?
}
