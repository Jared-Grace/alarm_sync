package com.example.alarmsync

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val text = intent.getStringExtra("text") ?: "Alarm"
        val i = Intent(context, AlarmActivity::class.java).apply {
            putExtra("text", text)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        context.startActivity(i)
    }
}