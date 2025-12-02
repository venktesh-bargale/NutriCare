package com.nutricare.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "meal_foods",
    foreignKeys = [
        ForeignKey(
            entity = Meal::class,
            parentColumns = ["mealId"],
            childColumns = ["mealId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FoodItem::class,
            parentColumns = ["foodId"],
            childColumns = ["foodId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MealFood(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val mealId: String = "",
    val foodId: String = "",
    val foodName: String = "",
    val quantity: Double = 0.0,
    val unit: String = "g",
    val calories: Double = 0.0,
    val protein: Double = 0.0,
    val carbs: Double = 0.0,
    val fats: Double = 0.0,
    val addedAt: Long = System.currentTimeMillis()
)
