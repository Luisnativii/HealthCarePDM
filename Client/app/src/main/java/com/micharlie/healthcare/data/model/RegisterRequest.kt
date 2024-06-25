package com.micharlie.healthcare.data.model


data class RegisterRequest(
    val name: String,
    val email: String,
    val gender: String,
    val dateBirth: String,
    val password: String
)
/*{
    val age: Int
        get() = 2024 - dateBirth.toInt()
}*/