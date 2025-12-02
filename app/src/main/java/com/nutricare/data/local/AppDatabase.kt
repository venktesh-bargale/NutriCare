package com.nutricare.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nutricare.data.models.*
import com.nutricare.util.Constants

@Database(
    entities = [
        User::class,
        Meal::class,
        MealFood::class,
        NutrientData::class,
        DailyGoal::class,
        FoodItem::class,
        WeeklyStats::class,
        UserPreferences::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mealDao(): MealDao
    abstract fun mealFoodDao(): MealFoodDao
    abstract fun nutrientDataDao(): NutrientDataDao
    abstract fun dailyGoalDao(): DailyGoalDao
    abstract fun foodItemDao(): FoodItemDao
    abstract fun weeklyStatsDao(): WeeklyStatsDao
    abstract fun userPreferencesDao(): UserPreferencesDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                Constants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
