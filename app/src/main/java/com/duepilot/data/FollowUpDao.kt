package com.duepilot.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.duepilot.data.entity.FollowUpEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowUpDao {
    @Insert
    suspend fun insert(f: FollowUpEntity)
    @Query("SELECT * FROM follow_ups WHERE invoiceId = :invId ORDER BY date DESC")
    fun byInvoice(invId: String): Flow<List<FollowUpEntity>>
}
