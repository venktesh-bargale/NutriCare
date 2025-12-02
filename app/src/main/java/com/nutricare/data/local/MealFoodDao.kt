package com.nutricare.data.local

import androidx.room.*
import com.nutricare.data.models.MealFood

@Dao
interface MealFoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealFood(mealFood: MealFood)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleMealFoods(mealFoods: List<MealFood>)

    @Query("SELECT * FROM meal_foods WHERE id = :id")
    suspend fun getMealFoodById(id: String): MealFood?

    @Query("SELECT * FROM meal_foods WHERE mealId = :mealId ORDER BY addedAt DESC")
    suspend fun getMealFoodsByMealId(mealId: String): List<MealFood>

    @Query("SELECT * FROM meal_foods WHERE foodId = :foodId")
    suspend fun getMealFoodsByFoodId(foodId: String): List<MealFood>

    @Update
    suspend fun updateMealFood(mealFood: MealFood)

    @Delete
    suspend fun deleteMealFood(mealFood: MealFood)

    @Query("DELETE FROM meal_foods WHERE mealId = :mealId")
    suspend fun deleteMealFoodsByMealId(mealId: String)

    @Query("SELECT COUNT(*) FROM meal_foods WHERE mealId = :mealId")
    suspend fun getMealFoodCountByMealId(mealId: String): Int

    @Query("""
        SELECT SUM(calories) FROM meal_foods 
        WHERE mealId = :mealId
    """)
    suspend fun getTotalCaloriesByMealId(mealId: String): Double?
}
