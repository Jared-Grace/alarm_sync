package com.example.alarmsync

import android.content.Context
import androidx.room.Room

class AlarmRepository(context: Context) {

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "alarms.db"
    ).build()

    val dao = db.dao()
}