package com.nutricare.data.repository

import com.nutricare.data.remote.NutritionixApiService
import com.nutricare.util.Constants
import com.nutricare.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodSearchRepository @Inject constructor(
    private val apiService: NutritionixApiService
) {

    // ✅ FUNCTION 1: Search Foods
    suspend fun searchFoods(query: String, pageSize: Int = 20): Result<List<Any>> =
        withContext(Dispatchers.IO) {
            try {
                // 🔴 THIS IS WHERE API IS CALLED
                val response = apiService.searchFoods(
                    query = query,
                    pageSize = pageSize,
                    apiKey = Constants.NUTRITIONIX_API_KEY  // ✅ Using your API key
                )

                if (response.foods.isNotEmpty()) {
                    Result.Success(response.foods)
                } else {
                    Result.Error(Exception("No foods found for '$query'"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    // ✅ FUNCTION 2: Get Food Details
    suspend fun getFoodById(fdcId: String): Result<Any> =
        withContext(Dispatchers.IO) {
            try {
                // 🔴 THIS IS WHERE API IS CALLED
                val response = apiService.getFoodById(
                    fdcId = fdcId,
                    apiKey = Constants.NUTRITIONIX_API_KEY  // ✅ Using your API key
                )

                if (response.foods.isNotEmpty()) {
                    Result.Success(response.foods[0])
                } else {
                    Result.Error(Exception("Food not found"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}