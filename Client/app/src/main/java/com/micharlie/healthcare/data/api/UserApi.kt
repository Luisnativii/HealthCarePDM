package com.micharlie.healthcare.data.api

import retrofit2.Call
import com.google.gson.annotations.SerializedName
import com.micharlie.healthcare.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


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

data class Comment(

    @SerializedName(value = Constants.CONTENT)
    val content: String,

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
    @GET(Constants.GETDATAUSER)
    fun getUsers(@Header("Authorization") token: String): Call<List<dataApi>>

    @Headers("Content-Type: application/json")
    @PATCH(Constants.UPDATE_DATA_USER)
    fun updateHeight(@Header("Authorization") token: String, @Body heightData: Map<String, Int>): Call<Void>


    @Headers("Content-Type: application/json")
    @PATCH(Constants.UPDATE_DATA_USER)
    fun updateWeight(@Header("Authorization") token: String, @Body weightData: Map<String, Int>): Call<Void>

    @Headers("Content-Type: application/json")
    @PATCH(Constants.UPDATE_DATA_USER)
    fun updateMuscularMass(@Header("Authorization") token: String, @Body muscularMassData: Map<String, Int>): Call<Void>

    @Headers("Content-Type: application/json")
    @PATCH(Constants.UPDATE_DATA_USER)
    fun updateBodyFat(@Header("Authorization") token: String, @Body bodyFatData: Map<String, Float>): Call<Void>

    @Headers("Content-Type: application/json")
    @PATCH(Constants.UPDATE_DATA_USER)
    fun updateCholesterol(@Header("Authorization") token: String, @Body cholesterolData: Map<String, Int>): Call<Void>

    @Headers("Content-Type: application/json")
    @PATCH(Constants.UPDATE_DATA_USER)
    fun updateBloodGlucose(@Header("Authorization") token: String, @Body bloodGlucoseData: Map<String, Float>): Call<Void>

    @Headers("Content-Type: application/json")
    @PATCH(Constants.UPDATE_DATA_USER)
    fun updateBloodPressure(@Header("Authorization") token: String, @Body bloodPressureData: Map<String, String>): Call<Void>


    @Headers("Content-Type: application/json")
    @POST(Constants.POST_COMMENT_PATH)
    fun postComment(@Header("Authorization") token: String, @Body commentData: Map<String, String>): Call<Void>

    @Headers("Content-Type: application/json")
    @DELETE("https://healthcare-api-production-46a3.up.railway.app/api/community/{id}")
    fun deleteVideo(@Path("id") id: String): Call<Void>


}

interface TokenCallback {
    fun onTokenReceived(token: String)
}




