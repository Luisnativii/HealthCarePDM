package com.micharlie.healthcare.data.api

import retrofit2.Call
import com.google.gson.annotations.SerializedName
import com.micharlie.healthcare.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST



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
    val data: List<dataApi> = arrayListOf(),
)

data class dataApi(

    @SerializedName(value = "_id")
    val id: String? = "",

    @SerializedName(value = Constants.HEIGHT)
    val height: String? = "",

    @SerializedName(value = Constants.WEIGHT)
    val weight: String? = "",

    @SerializedName(value = Constants.MUSCULARMASS)
    val muscularMass: String? = "",

    @SerializedName(value = Constants.BODYFAT)
    val bodyFat: Double? = null,

    @SerializedName(value = Constants.CHOLESTEROL)
    val cholesterol: String? = "",

    @SerializedName(value = Constants.BLOODGLUCOSE)
    val bloodGlucose: String? = "",

    @SerializedName(value = Constants.BLOODPRESSURE)
    val bloodPressure: String? = "",

    @SerializedName(value = Constants.DATE)
    val date: String? = "",
)

interface UserApiService {
    @Headers("Content-Type: application/json")
    @POST(Constants.POSTUSERBACEURL)
    fun postUser(@Body user: UserApi): Call<String>



    @Headers("Content-Type: application/json")
    @POST(Constants.POSTLOGINBACEURL)
    fun loginUser(@Body loginData: UserApi): Call<String>

    //no se porque putas usa USERAPI

    @Headers("Content-Type: application/json")
    @GET(Constants.GETDATAUSER) // Reemplaza esto con la ruta correcta de tu API
    fun getUsers(@Header("Authorization") token: String): Call<List<dataApi>>

    @Headers("Content-Type: application/json")
    @PATCH(Constants.UPDATE_HEIGHT_URL) // Reemplaza esto con la ruta correcta de tu API
    fun updateHeight(@Header("Authorization") token: String, @Body heightData: Map<String, Int>): Call<Void>
}

interface TokenCallback {
    fun onTokenReceived(token: String)
}




