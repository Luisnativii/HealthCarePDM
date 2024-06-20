package com.micharlie.healthcare.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(private val apiService: ApiService) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Success)
    val loginState: StateFlow<LoginState> = _loginState

    /*fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = apiService.login(request)
                if (response.isSuccessful) {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error("Invalid credentials")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Login failed")
            }
        }
    }*/
}