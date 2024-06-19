package com.micharlie.healthcare.ui.profile

import com.micharlie.healthcare.data.model.UserProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    fun loadUserProfile() {
        viewModelScope.launch {
            // Aquí deberías hacer una llamada a tu API o base de datos para obtener el perfil del usuario
            // Por ahora, usamos datos de ejemplo
            val profile = UserProfile(
                name = "John Doe",
                email = "john.doe@example.com",
                password = "******",
                gender = "Male",
                age = "30",
                height = "180",
                weight = "75",
                physical = "Fit"
            )
            _userProfile.value = profile
        }
    }
}
