package com.micharlie.healthcare.data.api


import com.google.gson.annotations.SerializedName
import com.micharlie.healthcare.utils.Constants

data class UserApi(
    @SerializedName(value = Constants.NAME)
    val name: String? = "",

    @SerializedName(value = Constants.EMAIL)
    val email: String? = "",

    @SerializedName(value = Constants.PASSWORD)
    val password: String? = "",

    @SerializedName(value = Constants.GENDER)
    val gender: String? = "",

    @SerializedName(value = Constants.AGE)
    val age: Int? = 0,

    @SerializedName(value = Constants.HEIGHT)
    val height: Float? = 0f,

    @SerializedName(value = Constants.WEIGHT)
    val weight: Float? = 0f,

    @SerializedName(value = Constants.PHYSICAL)
    val physical: String? = ""
)