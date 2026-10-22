package com.duepilot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.duepilot.data.entity.ClientEntity
import com.duepilot.data.entity.InvoiceEntity
import com.duepilot.data.entity.FollowUpEntity

@Database(entities = [ClientEntity::class, InvoiceEntity::class, FollowUpEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class DuePilotDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun followUpDao(): FollowUpDao
    abstract fun reminderDao(): ReminderDao
    abstract fun insightsDao(): InsightsDao

    companion object {
        @Volatile private var INSTANCE: DuePilotDatabase? = null
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS reminders (id TEXT PRIMARY KEY NOT NULL, invoiceId TEXT, followUpId TEXT, clientId TEXT, scheduledMs INTEGER NOT NULL, enabled INTEGER NOT NULL DEFAULT 1, note TEXT, createdAt INTEGER NOT NULL, updatedAt INTEGER NOT NULL)")
                db.execSQL("CREATE INDEX IF NOT EXISTS idx_reminders_invoice ON reminders(invoiceId)")
            }
        }
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS clients (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, email TEXT, company TEXT, notes TEXT, createdAt INTEGER NOT NULL)")
                db.execSQL("CREATE TABLE IF NOT EXISTS invoices (id TEXT PRIMARY KEY NOT NULL, invoiceNumber TEXT NOT NULL, clientId TEXT NOT NULL, description TEXT, amountMinor INTEGER NOT NULL, currencyCode TEXT NOT NULL, status TEXT NOT NULL, issueDate INTEGER NOT NULL, dueDate INTEGER NOT NULL, notes TEXT, createdAt INTEGER NOT NULL, updatedAt INTEGER NOT NULL)")
                db.execSQL("CREATE INDEX IF NOT EXISTS idx_invoices_client ON invoices(clientId)")
                db.execSQL("CREATE INDEX IF NOT EXISTS idx_invoices_status ON invoices(status)")
            }
        }
        fun getDatabase(context: Context): DuePilotDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext, DuePilotDatabase::class.java, "duepilot_db").addMigrations(MIGRATION_1_2, MIGRATION_2_3).build().also { INSTANCE = it }
            }
        }
    }
}
