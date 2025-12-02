package com.nutricare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutricare.data.repository.FoodSearchRepository
import com.nutricare.util.Logger
import com.nutricare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodSearchViewModel @Inject constructor(
    private val foodSearchRepository: FoodSearchRepository
) : ViewModel() {

    // ✅ Holds the search results
    private val _searchResults = MutableLiveData<List<Any>>()
    val searchResults: LiveData<List<Any>> = _searchResults

    // ✅ Loading state for UI
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // ✅ Error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    // ✅ FUNCTION: Called from Fragment when user searches
    fun searchFoods(query: String) {
        if (query.isBlank()) {
            _errorMessage.value = "Please enter a food name"
            return
        }

        _isLoading.value = true
        _errorMessage.value = ""

        viewModelScope.launch {
            // 🔴 THIS CALLS THE REPOSITORY WHICH CALLS THE API
            val result = foodSearchRepository.searchFoods(query)

            when (result) {
                is Result.Success -> {
                    _searchResults.value = result.data
                    _isLoading.value = false
                    Logger.i("Search results: ${result.data.size} foods found")
                }
                is Result.Error -> {
                    _errorMessage.value = result.exception.message ?: "Search failed"
                    _searchResults.value = emptyList()
                    _isLoading.value = false
                    Logger.e("SearchViewModel", "Search error", result.exception)
                }
                is Result.Loading -> {}
            }
        }
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
        _errorMessage.value = ""
    }
}