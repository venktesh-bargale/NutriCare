package com.nutricare.data.local

import androidx.room.*
import com.nutricare.data.models.FoodItem

@Dao
interface FoodItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItem(foodItem: FoodItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleFoodItems(foodItems: List<FoodItem>)

    @Query("SELECT * FROM food_items WHERE foodId = :foodId")
    suspend fun getFoodItemById(foodId: String): FoodItem?

    @Query("SELECT * FROM food_items WHERE foodName LIKE '%' || :query || '%'")
    suspend fun searchFoodItems(query: String): List<FoodItem>

    @Query("SELECT * FROM food_items ORDER BY cachedAt DESC")
    suspend fun getAllFoodItems(): List<FoodItem>

    @Query("SELECT * FROM food_items ORDER BY cachedAt DESC LIMIT :limit")
    suspend fun getRecentFoodItems(limit: Int): List<FoodItem>

    @Update
    suspend fun updateFoodItem(foodItem: FoodItem)

    @Delete
    suspend fun deleteFoodItem(foodItem: FoodItem)

    @Query("DELETE FROM food_items")
    suspend fun deleteAllFoodItems()

    @Query("SELECT COUNT(*) FROM food_items")
    suspend fun getFoodItemCount(): Int
}
