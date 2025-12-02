package com.nutricare.di

import com.nutricare.data.local.DailyGoalDao
import com.nutricare.data.local.MealDao
import com.nutricare.data.local.MealFoodDao
import com.nutricare.data.local.NutrientDataDao
import com.nutricare.data.local.UserDao
import com.nutricare.data.remote.NutritionixApiService
import com.nutricare.data.repository.FoodSearchRepository
import com.nutricare.data.repository.MealRepository
import com.nutricare.data.repository.NutritionRepository
import com.nutricare.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository =
        UserRepository(userDao)

    @Singleton
    @Provides
    fun provideMealRepository(
        mealDao: MealDao,
        mealFoodDao: MealFoodDao
    ): MealRepository =
        MealRepository(mealDao, mealFoodDao)

    @Singleton
    @Provides
    fun provideNutritionRepository(
        nutrientDataDao: NutrientDataDao,
        dailyGoalDao: DailyGoalDao
    ): NutritionRepository =
        NutritionRepository(nutrientDataDao, dailyGoalDao)

    // ✅ NEW: Register FoodSearchRepository
    @Singleton
    @Provides
    fun provideFoodSearchRepository(
        apiService: NutritionixApiService
    ): FoodSearchRepository =
        FoodSearchRepository(apiService)  // ✅ This provides the API service to repository
}