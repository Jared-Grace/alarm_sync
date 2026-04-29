package com.example.alarmsync

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Query("SELECT * FROM AlarmEntity ORDER BY dateTime ASC")
    fun getAll(): Flow<List<AlarmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<AlarmEntity>)

    @Query("DELETE FROM AlarmEntity")
    suspend fun clearAll()
}