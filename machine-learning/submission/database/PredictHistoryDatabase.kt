package com.dicoding.asclepius.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PredictHistory::class], version = 1)
abstract class PredictHistoryDatabase : RoomDatabase() {
    abstract fun predictHistoryDao(): PredictHistoryDao
    companion object {
        @Volatile
        private var INSTANCE: PredictHistoryDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): PredictHistoryDatabase {
            if (INSTANCE == null) {
                synchronized(PredictHistoryDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PredictHistoryDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as PredictHistoryDatabase
        }
    }
}