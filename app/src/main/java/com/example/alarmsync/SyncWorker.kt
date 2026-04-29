package com.example.alarmsync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

import android.util.Log

class SyncWorker(ctx: Context, params: WorkerParameters)
    : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        Log.d("ALARMAPP", "Worker started")

        val prefs = applicationContext.getSharedPreferences("app", Context.MODE_PRIVATE)
        val url = prefs.getString("url", null) ?: return Result.failure()

        val retrofit = Retrofit.Builder()
            .baseUrl(url.substringBeforeLast("/") + "/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        val remote = api.getAlarms(url)

        val repo = AlarmRepository(applicationContext)

        // clear old alarms
        repo.dao.clearAll()

        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        val alarms = remote.mapIndexedNotNull { _, dto ->

            val calendar = Calendar.getInstance()
            val partsDate = dto.date.split("-")
            val partsTime = dto.time.split(":")

            calendar.set(
                partsDate[0].toInt(),
                partsDate[1].toInt() - 1,
                partsDate[2].toInt(),
                partsTime[0].toInt(),
                partsTime[1].toInt(),
                0
            )

            //val dt = calendar.timeInMillis

            val dt = System.currentTimeMillis() + 60_000 // 1 min from now

            Log.d("ALARMAPP", "Scheduling: ${dto.date} ${dto.time} -> $dt")
            Log.d("ALARMAPP", "Now: ${System.currentTimeMillis()}")
            Log.d("ALARMAPP", "Diff: ${dt - System.currentTimeMillis()}")

            AlarmEntity(
                id = (dto.date + dto.time).hashCode(),
                dateTime = dt,
                text = dto.text,
                requestCode = dt.hashCode()
            )
        }

        Log.d("ALARMAPP", "Fetched: ${remote.size} alarms")

        repo.dao.insertAll(alarms)

        alarms.forEach {
            AlarmScheduler.schedule(applicationContext, it)
        }

        Log.d("ALARMAPP", "Worker finished")

        return Result.success()
    }
}