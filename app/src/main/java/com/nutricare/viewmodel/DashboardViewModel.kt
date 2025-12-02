package com.nutricare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutricare.data.models.Meal
import com.nutricare.data.repository.MealRepository
import com.nutricare.data.repository.NutritionRepository
import com.nutricare.data.repository.UserRepository
import com.nutricare.util.DateUtils
import com.nutricare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val nutritionRepository: NutritionRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _todayMeals = MutableLiveData<List<Meal>>()
    val todayMeals: LiveData<List<Meal>> = _todayMeals

    private val _totalCalories = MutableLiveData<Double>()
    val totalCalories: LiveData<Double> = _totalCalories

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun loadTodayData(userId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val today = DateUtils.getTodayDate()
                val caloriesResult = mealRepository.getTotalCaloriesByDate(userId, today)

                when (caloriesResult) {
                    is Result.Success -> {
                        _totalCalories.value = caloriesResult.data
                    }
                    is Result.Error -> {
                        _errorMessage.value = "Failed to load calories"
                    }
                    is Result.Loading -> {}
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshData(userId: String) {
        loadTodayData(userId)
    }
}
