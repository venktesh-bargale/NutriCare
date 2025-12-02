package com.nutricare.data.repository

import androidx.lifecycle.LiveData
import com.nutricare.data.local.MealDao
import com.nutricare.data.local.MealFoodDao
import com.nutricare.data.models.Meal
import com.nutricare.data.models.MealFood
import com.nutricare.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val mealDao: MealDao,
    private val mealFoodDao: MealFoodDao
) {

    suspend fun insertMeal(meal: Meal): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            mealDao.insertMeal(meal)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun insertMealWithFoods(meal: Meal, foods: List<MealFood>): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                mealDao.insertMeal(meal)
                mealFoodDao.insertMultipleMealFoods(foods)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    fun getMealsByUserId(userId: String): LiveData<List<Meal>> =
        mealDao.getMealsByUserId(userId)

    fun getMealsByDate(userId: String, date: String): LiveData<List<Meal>> =
        mealDao.getMealsByDate(userId, date)

    suspend fun getMealById(mealId: String): Result<Meal?> = withContext(Dispatchers.IO) {
        try {
            val meal = mealDao.getMealById(mealId)
            Result.Success(meal)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getMealFoodsByMealId(mealId: String): Result<List<MealFood>> =
        withContext(Dispatchers.IO) {
            try {
                val foods = mealFoodDao.getMealFoodsByMealId(mealId)
                Result.Success(foods)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    suspend fun updateMeal(meal: Meal): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            mealDao.updateMeal(meal)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun deleteMeal(meal: Meal): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            mealFoodDao.deleteMealFoodsByMealId(meal.mealId)
            mealDao.deleteMeal(meal)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getTotalCaloriesByDate(userId: String, date: String): Result<Double> =
        withContext(Dispatchers.IO) {
            try {
                val calories = mealDao.getTotalCaloriesByDate(userId, date) ?: 0.0
                Result.Success(calories)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    suspend fun getMealCountByUserId(userId: String): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val count = mealDao.getMealCountByUserId(userId)
            Result.Success(count)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
