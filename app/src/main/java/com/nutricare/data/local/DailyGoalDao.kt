package com.nutricare.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nutricare.data.models.DailyGoal

@Dao
interface DailyGoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyGoal(dailyGoal: DailyGoal)

    @Query("SELECT * FROM daily_goals WHERE id = :id")
    suspend fun getDailyGoalById(id: String): DailyGoal?

    @Query("SELECT * FROM daily_goals WHERE userId = :userId AND date = :date")
    suspend fun getDailyGoalByDate(userId: String, date: String): DailyGoal?

    @Query("SELECT * FROM daily_goals WHERE userId = :userId ORDER BY date DESC")
    fun getDailyGoalsByUserId(userId: String): LiveData<List<DailyGoal>>

    @Query("SELECT * FROM daily_goals WHERE userId = :userId ORDER BY date DESC LIMIT 1")
    suspend fun getLatestDailyGoal(userId: String): DailyGoal?

    @Update
    suspend fun updateDailyGoal(dailyGoal: DailyGoal)

    @Delete
    suspend fun deleteDailyGoal(dailyGoal: DailyGoal)

    @Query("DELETE FROM daily_goals WHERE userId = :userId")
    suspend fun deleteDailyGoalsByUserId(userId: String)
}
