package com.nutricare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutricare.data.models.Meal
import com.nutricare.data.models.MealFood
import com.nutricare.data.repository.MealRepository
import com.nutricare.util.DateUtils
import com.nutricare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealLogViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String> = _successMessage

    fun loadMealsByDate(userId: String, date: String = DateUtils.getTodayDate()) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                mealRepository.getMealsByDate(userId, date).observeForever { meals ->
                    _meals.value = meals
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to load meals"
                _isLoading.value = false
            }
        }
    }

    fun addMeal(meal: Meal, foods: List<MealFood> = emptyList()) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = mealRepository.insertMealWithFoods(meal, foods)
            when (result) {
                is Result.Success -> {
                    _successMessage.value = "Meal added successfully"
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _errorMessage.value = result.exception.message ?: "Failed to add meal"
                    _isLoading.value = false
                }
                is Result.Loading -> {}
            }
        }
    }

    fun deleteMeal(meal: Meal) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = mealRepository.deleteMeal(meal)
            when (result) {
                is Result.Success -> {
                    _successMessage.value = "Meal deleted successfully"
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _errorMessage.value = result.exception.message ?: "Failed to delete meal"
                    _isLoading.value = false
                }
                is Result.Loading -> {}
            }
        }
    }
}
