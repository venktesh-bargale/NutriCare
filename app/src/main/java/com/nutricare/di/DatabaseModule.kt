package com.nutricare.di

import android.content.Context
import androidx.room.Room
import com.nutricare.data.local.*
import com.nutricare.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao =
        database.userDao()

    @Singleton
    @Provides
    fun provideMealDao(database: AppDatabase): MealDao =
        database.mealDao()

    @Singleton
    @Provides
    fun provideMealFoodDao(database: AppDatabase): MealFoodDao =
        database.mealFoodDao()

    @Singleton
    @Provides
    fun provideNutrientDataDao(database: AppDatabase): NutrientDataDao =
        database.nutrientDataDao()

    @Singleton
    @Provides
    fun provideDailyGoalDao(database: AppDatabase): DailyGoalDao =
        database.dailyGoalDao()

    @Singleton
    @Provides
    fun provideFoodItemDao(database: AppDatabase): FoodItemDao =
        database.foodItemDao()
}
