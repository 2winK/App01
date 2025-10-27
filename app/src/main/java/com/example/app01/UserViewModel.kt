package com.example.app01

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    val errorMessage = mutableStateOf<String?>(null)

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                _users.value = RetrofitClient.apiService.getUsers()
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "Помилка завантаження: ${e.message}"
            }
        }
    }
}