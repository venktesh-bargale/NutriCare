package com.nutricare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nutricare.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    fun setCurrentUser(user: User?) {
        _currentUser.value = user
    }

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun navigateTo(destination: String) {
        _navigationEvent.value = NavigationEvent(destination)
    }

    data class NavigationEvent(val destination: String)
}
