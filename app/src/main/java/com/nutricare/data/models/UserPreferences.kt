package com.nutricare.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "user_preferences",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserPreferences(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val userId: String = "",
    val dailyCalorieGoal: Double = 2000.0,
    val dailyProteinGoal: Double = 150.0,
    val dailyCarbsGoal: Double = 250.0,
    val dailyFatsGoal: Double = 65.0,
    val notificationsEnabled: Boolean = true,
    val theme: String = "light",
    val language: String = "en",
    val updatedAt: Long = System.currentTimeMillis()
)
