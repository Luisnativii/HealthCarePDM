package com.micharlie.healthcare.data.model

data class UserProfile(
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val age: String,
    val height: String,
    val weight: String,
    val physical: String
)