package com.example.app01

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployeesViewModel(private val apiDataSource: ApiDataSource) : ViewModel() {
    val employees = mutableStateOf<List<Employee>>(emptyList())
    val errorMessage = mutableStateOf<String?>(null)

    init {
        loadEmployees()
    }

    private fun loadEmployees() {
        viewModelScope.launch {
            try {
                val result = apiDataSource.getEmployees()
                employees.value = result
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "Помилка завантаження: ${e.message}"
            }
        }
    }
}