package com.nutricare.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "daily_goals",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DailyGoal(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val userId: String = "",
    val date: String = "",
    val targetCalories: Double = 2000.0,
    val targetProtein: Double = 150.0,
    val targetCarbs: Double = 250.0,
    val targetFats: Double = 65.0,
    val createdAt: Long = System.currentTimeMillis()
)
