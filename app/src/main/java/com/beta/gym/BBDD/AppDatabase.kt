package com.beta.gym.BBDD

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Crono::class],//añadido
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val cronoDao: CronoDao

    companion object {
        const val DATABASE_NAME = "db-roomdb2"
    }
}