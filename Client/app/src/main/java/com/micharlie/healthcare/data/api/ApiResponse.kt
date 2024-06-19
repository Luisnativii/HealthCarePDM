package com.micharlie.healthcare.data.api

import com.google.gson.annotations.SerializedName
import com.micharlie.healthcare.utils.Constants

data class ApiResponseSuccessful(
    @SerializedName(value = Constants.RESPONSE_SUCCESSFUL)
    val result : String

)

data class ApiResponseError(

    @SerializedName(value = Constants.RESPONSE_ERROR)
    val message: String
)