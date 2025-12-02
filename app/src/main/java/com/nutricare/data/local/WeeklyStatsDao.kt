package com.nutricare.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nutricare.data.models.WeeklyStats

@Dao
interface WeeklyStatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeklyStats(weeklyStats: WeeklyStats)

    @Query("SELECT * FROM weekly_stats WHERE id = :id")
    suspend fun getWeeklyStatsById(id: String): WeeklyStats?

    @Query("SELECT * FROM weekly_stats WHERE userId = :userId ORDER BY weekStart DESC")
    fun getWeeklyStatsByUserId(userId: String): LiveData<List<WeeklyStats>>

    @Query("SELECT * FROM weekly_stats WHERE userId = :userId ORDER BY weekStart DESC LIMIT 1")
    suspend fun getLatestWeeklyStats(userId: String): WeeklyStats?

    @Update
    suspend fun updateWeeklyStats(weeklyStats: WeeklyStats)

    @Delete
    suspend fun deleteWeeklyStats(weeklyStats: WeeklyStats)

    @Query("DELETE FROM weekly_stats WHERE userId = :userId")
    suspend fun deleteWeeklyStatsByUserId(userId: String)
}
