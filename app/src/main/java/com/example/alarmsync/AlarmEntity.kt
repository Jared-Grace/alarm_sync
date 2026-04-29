package com.example.alarmsync

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlarmEntity(
    @PrimaryKey val id: Int,
    val dateTime: Long,
    val text: String,
    val requestCode: Int
)