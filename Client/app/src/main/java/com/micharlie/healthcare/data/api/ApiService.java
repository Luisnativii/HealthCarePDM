package com.micharlie.healthcare.data.api;

import com.micharlie.healthcare.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiService {

    //@Headers(value = ["Content-Type: application/json"])
    //@POST(value = Constants.POST_USER_PATH)
    //suspend fun postUser(@Body user: UserApi): ApiResponseSuccessful
    @Headers({"Content-Type: application/json"})
    @POST(Constants.POST_USER_PATH)
    Call<ApiResponseSuccessful> postUser(@Body UserApi user);
}
