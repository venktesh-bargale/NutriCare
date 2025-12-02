package com.nutricare.data.repository

import androidx.lifecycle.LiveData
import com.nutricare.data.local.DailyGoalDao
import com.nutricare.data.local.NutrientDataDao
import com.nutricare.data.models.DailyGoal
import com.nutricare.data.models.NutrientData
import com.nutricare.util.DateUtils
import com.nutricare.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NutritionRepository @Inject constructor(
    private val nutrientDataDao: NutrientDataDao,
    private val dailyGoalDao: DailyGoalDao
) {

    suspend fun insertNutrientData(nutrientData: NutrientData): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                nutrientDataDao.insertNutrientData(nutrientData)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    fun getNutrientDataByUserId(userId: String): LiveData<List<NutrientData>> =
        nutrientDataDao.getNutrientDataByUserId(userId)

    suspend fun getNutrientDataByDate(userId: String, date: String): Result<NutrientData?> =
        withContext(Dispatchers.IO) {
            try {
                val data = nutrientDataDao.getNutrientDataByDate(userId, date)
                Result.Success(data)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    suspend fun getNutrientDataRange(
        userId: String,
        startDate: String,
        endDate: String
    ): Result<List<NutrientData>> = withContext(Dispatchers.IO) {
        try {
            val data = nutrientDataDao.getNutrientDataRange(userId, startDate, endDate)
            Result.Success(data)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun updateNutrientData(nutrientData: NutrientData): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                nutrientDataDao.updateNutrientData(nutrientData)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    suspend fun insertDailyGoal(dailyGoal: DailyGoal): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            dailyGoalDao.insertDailyGoal(dailyGoal)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun getDailyGoalsByUserId(userId: String): LiveData<List<DailyGoal>> =
        dailyGoalDao.getDailyGoalsByUserId(userId)

    suspend fun getLatestDailyGoal(userId: String): Result<DailyGoal?> =
        withContext(Dispatchers.IO) {
            try {
                val goal = dailyGoalDao.getLatestDailyGoal(userId)
                Result.Success(goal)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    suspend fun getDailyGoalByDate(userId: String, date: String): Result<DailyGoal?> =
        withContext(Dispatchers.IO) {
            try {
                val goal = dailyGoalDao.getDailyGoalByDate(userId, date)
                Result.Success(goal)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    suspend fun updateDailyGoal(dailyGoal: DailyGoal): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            dailyGoalDao.updateDailyGoal(dailyGoal)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
