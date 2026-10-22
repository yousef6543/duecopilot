package com.duepilot.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.duepilot.data.entity.InvoiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(inv: InvoiceEntity)

    @Update
    suspend fun update(inv: InvoiceEntity)

    @Delete
    suspend fun delete(inv: InvoiceEntity)

    @Query("SELECT * FROM invoices WHERE id = :id")
    suspend fun getById(id: String): InvoiceEntity?

    @Query("SELECT * FROM invoices WHERE invoiceNumber = :num")
    suspend fun getByNumber(num: String): InvoiceEntity?

    @Query("SELECT * FROM invoices WHERE clientId = :clientId ORDER BY dueDate ASC")
    fun byClient(clientId: String): Flow<List<InvoiceEntity>>

    @Query("SELECT * FROM invoices WHERE status = :status ORDER BY dueDate ASC")
    fun byStatus(status: String): Flow<List<InvoiceEntity>>

    @Query("SELECT * FROM invoices WHERE dueDate <= :now AND status != 'Paid'")
    fun overdueBefore(now: Long): Flow<List<InvoiceEntity>>

    @Query("SELECT COUNT(*) FROM invoices WHERE invoiceNumber = :num")
    suspend fun countByNumber(num: String): Int

    @Query("SELECT * FROM invoices ORDER BY createdAt DESC LIMIT 50")
    fun recent(): Flow<List<InvoiceEntity>>
}
