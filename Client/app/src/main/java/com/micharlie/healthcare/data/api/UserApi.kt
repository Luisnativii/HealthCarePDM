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

    @SerializedName(value = Constants.DATEBIRTH)
    val dateBirth: String? = "",

    @SerializedName(value = Constants.DATA)
    val FamilyMembers: List<dataApi> = arrayListOf(),
)

data class dataApi(


    @SerializedName(value = Constants.HEIGHT)
    val height: String? = "",

    @SerializedName(value = Constants.WEIGHT)
    val weight: String? = "",

    @SerializedName(value = Constants.MUSCULARMASS)
    val muscularMass: String? = "",

    @SerializedName(value = Constants.BODYFAT)
    val bodyFat: Boolean? = false,

    @SerializedName(value = Constants.CHOLESTEROL)
    val cholesterol: String? = "",

    @SerializedName(value = Constants.BLOODGLUCOSE)
    val bloodGlucose: String? = "",

    @SerializedName(value = Constants.BLOODPRESSURE)
    val bloodPressure: String? = "",

    @SerializedName(value = Constants.DATE)
    val date: String? = "",
)


//name
//email
//age
//gender
//password

//data

//height
//weight
//muscularMass
//bodyFat
//cholesterol
//bloodGlucose
//bloodPressure
//date
