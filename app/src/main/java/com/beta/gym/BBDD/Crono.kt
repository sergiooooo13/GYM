package com.beta.gym.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crono")
data class Crono(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "week")
    val week: Int,
    @ColumnInfo(name = "runTime")
    val runTime: Long,
    @ColumnInfo(name = "restTime")
    val restTime: Long
)
