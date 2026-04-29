package com.example.alarmsync

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AlarmViewModel : ViewModel() {

    private lateinit var repo: AlarmRepository
    private lateinit var prefs: SharedPreferences

    val alarms = MutableStateFlow<List<AlarmEntity>>(emptyList())

    fun init(context: Context) {
        repo = AlarmRepository(context)
        prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE)

        viewModelScope.launch {
            repo.dao.getAll().collect {
                alarms.value = it
            }
        }
    }

    fun getUrl(): String {
        return prefs.getString("url", "https://example.com/alarms.json")!!
    }

    fun setUrl(url: String) {
        prefs.edit().putString("url", url).apply()
    }

    fun syncNow(context: Context) {
        val req = OneTimeWorkRequestBuilder<SyncWorker>().build()
        WorkManager.getInstance(context).enqueue(req)
    }
}