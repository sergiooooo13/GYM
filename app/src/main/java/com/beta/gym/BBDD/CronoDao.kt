package com.beta.gym.BBDD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CronoDao {
    @Insert
    suspend fun insert(crono: Crono)

    @Query("SELECT * FROM crono")
    suspend fun getAllUsers(): List<Crono>
}
