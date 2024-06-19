package com.micharlie.healthcare.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micharlie.healthcare.data.api.ApiService
import kotlinx.coroutines.launch


class LoginViewModel(private val apiService: ApiService) : ViewModel() {
    /*fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        viewModelScope.launch {
            try {
                val response = apiService.login(request)
                // manejar la respuesta exitosa
            } catch (e: Exception) {
                // manejar el error
            }
        }
    }*/
}