package com.nutricare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutricare.data.models.User
import com.nutricare.data.repository.UserRepository
import com.nutricare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String> = _successMessage

    fun loadUser(userId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = userRepository.getUserById(userId)
            when (result) {
                is Result.Success -> {
                    _user.value = result.data
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _errorMessage.value = result.exception.message ?: "Failed to load user"
                    _isLoading.value = false
                }
                is Result.Loading -> {}
            }
        }
    }

    fun createOrUpdateUser(user: User) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = userRepository.insertUser(user)
            when (result) {
                is Result.Success -> {
                    _user.value = user
                    _successMessage.value = "User profile updated"
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _errorMessage.value = result.exception.message ?: "Failed to update user"
                    _isLoading.value = false
                }
                is Result.Loading -> {}
            }
        }
    }

    fun deleteUser(user: User) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = userRepository.deleteUser(user)
            when (result) {
                is Result.Success -> {
                    _user.value = null
                    _successMessage.value = "User deleted"
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _errorMessage.value = result.exception.message ?: "Failed to delete user"
                    _isLoading.value = false
                }
                is Result.Loading -> {}
            }
        }
    }

    fun getFirstUser() {
        _isLoading.value = true
        val userLiveData = userRepository.getFirstUser()
        userLiveData.observeForever { user ->
            _user.value = user
            _isLoading.value = false
        }
    }
}
