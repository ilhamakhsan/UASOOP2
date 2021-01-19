package com.ilham.uasoop2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilham.uasoop2.model.Tugas

//Database annotation to specify the entities and set version
@Database(entities = [Tugas::class], version = 1, exportSchema = false)
abstract class TugasRomDB : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: TugasRomDB? = null

        fun getDatabase(context: Context): TugasRomDB {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TugasRomDB::class.java,
                    "tugas_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate database if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getTugasDao(): TugasDao
}