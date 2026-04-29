package com.example.alarmsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.alarmsync.ui.theme.AlarmSyncTheme

class AlarmActivity : ComponentActivity() {
    private var mediaPlayer: android.media.MediaPlayer? = null
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = intent.getStringExtra("text") ?: "Alarm"

        setContent {
            AlarmSyncTheme {
                Column {
                    Text(text, style = MaterialTheme.typography.titleLarge)
                    Button(onClick = {
                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null
                        finish()
                    }) {
                        Text("Dismiss", style = MaterialTheme.typography.titleLarge)
                    }
                    Button(onClick = {

                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null

                        val snoozedTime = System.currentTimeMillis() + 10 * 1000 // 5 min

                        val snoozedAlarm = AlarmEntity(
                            id = 0,
                            text = "Snoozed: $text",
                            dateTime = snoozedTime,
                            requestCode = (Math.random() * 100000).toInt()
                        )

                        AlarmScheduler.schedule(this@AlarmActivity, snoozedAlarm)

                        finish()

                    }) {
                        Text("Snooze", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }

            val alarmUri = android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI

            mediaPlayer = android.media.MediaPlayer().apply {
                setDataSource(this@AlarmActivity, alarmUri)

                setAudioAttributes(
                    android.media.AudioAttributes.Builder()
                        .setUsage(android.media.AudioAttributes.USAGE_ALARM)
                        .setContentType(android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )

                isLooping = true
                prepare()
                start()
            }
        }
    }
}