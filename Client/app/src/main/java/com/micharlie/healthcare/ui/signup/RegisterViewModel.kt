package com.micharlie.healthcare.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micharlie.healthcare.data.api.ApiClient
import com.micharlie.healthcare.data.api.ApiResponseSuccessful
import com.micharlie.healthcare.data.api.ApiService
import com.micharlie.healthcare.data.model.RegisterRequest
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val apiService: ApiService) : ViewModel() {
    /*fun register(user: RegisterRequest) {
        viewModelScope.launch {
            try {
                val response = apiService.register(user)
                // manejar la respuesta exitosa
            } catch (e: Exception) {
                // manejar el error
            }
        }
    }*/
}