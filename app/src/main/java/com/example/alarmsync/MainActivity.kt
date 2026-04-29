package com.example.alarmsync

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import android.provider.Settings

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val vm: AlarmViewModel = viewModel()
            vm.init(this)

            val alarms by vm.alarms.collectAsState()
            var url by remember { mutableStateOf(vm.getUrl()) }

            Column(Modifier.padding(16.dp)) {

                OutlinedTextField(
                    value = url,
                    onValueChange = {
                        url = it
                        vm.setUrl(it)
                    },
                    label = { Text("JSON URL") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                Button(onClick = { vm.syncNow(this@MainActivity) }) {
                    Text("Sync Now")
                }
                Button(onClick = {

                    val testAlarm = AlarmEntity(
                        id = 0,
                        text = "TEST ALARM",
                        dateTime = System.currentTimeMillis() + 5_000,
                        requestCode = 999
                    )

                    AlarmScheduler.schedule(this@MainActivity, testAlarm)

                }){
                    Text("Test alarm 5 seconds from now")
                }

                Spacer(Modifier.height(16.dp))

                LazyColumn {
                    items(alarms) {
                        Text("${it.text} - ${it.dateTime}")
                    }
                }
            }
        }
    }
}