package com.nutricare.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey
    val foodId: String = UUID.randomUUID().toString(),
    val foodName: String = "",
    val calories: Double = 0.0,
    val protein: Double = 0.0,
    val carbs: Double = 0.0,
    val fats: Double = 0.0,
    val servingSize: String = "100g",
    val apiId: String = "",
    val cachedAt: Long = System.currentTimeMillis()
)
