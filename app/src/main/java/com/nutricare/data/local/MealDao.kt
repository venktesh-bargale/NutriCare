package com.nutricare.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nutricare.data.models.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleMeals(meals: List<Meal>)

    @Query("SELECT * FROM meals WHERE mealId = :mealId")
    suspend fun getMealById(mealId: String): Meal?

    @Query("SELECT * FROM meals WHERE userId = :userId ORDER BY createdAt DESC")
    fun getMealsByUserId(userId: String): LiveData<List<Meal>>

    @Query("SELECT * FROM meals WHERE userId = :userId ORDER BY createdAt DESC LIMIT :limit")
    suspend fun getMealsByUserIdLimit(userId: String, limit: Int): List<Meal>

    @Query("""
        SELECT * FROM meals 
        WHERE userId = :userId AND date(createdAt/1000, 'unixepoch') = :date
        ORDER BY createdAt DESC
    """)
    fun getMealsByDate(userId: String, date: String): LiveData<List<Meal>>

    @Query("SELECT * FROM meals ORDER BY createdAt DESC")
    suspend fun getAllMeals(): List<Meal>

    @Update
    suspend fun updateMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("DELETE FROM meals WHERE userId = :userId")
    suspend fun deleteMealsByUserId(userId: String)

    @Query("SELECT COUNT(*) FROM meals WHERE userId = :userId")
    suspend fun getMealCountByUserId(userId: String): Int

    @Query("""
        SELECT SUM(totalCalories) FROM meals 
        WHERE userId = :userId AND date(createdAt/1000, 'unixepoch') = :date
    """)
    suspend fun getTotalCaloriesByDate(userId: String, date: String): Double?
}
