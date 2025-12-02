package com.nutricare.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NutritionixApiService {

    @GET("fdc/v1/foods/search")
    suspend fun searchFoods(
        @Query("query") query: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("api_key") apiKey: String
    ): FoodSearchResponse

    @GET("fdc/v1/foods")
    suspend fun getFoodById(
        @Query("fdcId") fdcId: String,
        @Query("api_key") apiKey: String
    ): FoodDetailResponse
}

data class FoodSearchResponse(
    val foods: List<FoodData> = emptyList(),
    val totalHits: Int = 0,
    val currentPage: Int = 0
)

data class FoodData(
    val fdcId: String = "",
    val description: String = "",
    val foodNutrients: List<Nutrient> = emptyList(),
    val servingSize: Double = 100.0,
    val servingSizeUnit: String = "g"
)

data class Nutrient(
    val nutrientId: Int = 0,
    val nutrientName: String = "",
    val value: Double = 0.0,
    val unitName: String = ""
)

data class FoodDetailResponse(
    val foods: List<FoodDetailData> = emptyList()
)

data class FoodDetailData(
    val fdcId: String = "",
    val description: String = "",
    val foodNutrients: List<Nutrient> = emptyList()
)
