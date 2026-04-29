package com.example.alarmsync

import retrofit2.http.GET
import retrofit2.http.Url

data class AlarmDto(
    val date: String,
    val time: String,
    val text: String
)

interface ApiService {
    @GET
    suspend fun getAlarms(@Url url: String): List<AlarmDto>
}