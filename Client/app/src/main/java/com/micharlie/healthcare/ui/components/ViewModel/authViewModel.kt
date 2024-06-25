package com.micharlie.healthcare.ui.components.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.micharlie.healthcare.data.api.TokenCallback
import com.micharlie.healthcare.data.api.UserApi
import com.micharlie.healthcare.data.api.UserApiService
import com.micharlie.healthcare.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class authViewModel (application: Application) : AndroidViewModel(application)  {
    private val viewModelContext = getApplication<Application>().applicationContext
    //Data Store
    val datastore = DataStore(viewModelContext)
    val token = MutableStateFlow<String>("")

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.GETDATAUSER) // Reemplaza esto con la URL base de tu API
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Crear una instancia de UserApiService
    private val userApiService = retrofit.create(UserApiService::class.java)





    fun saveToken(t: String){
        viewModelScope.launch (Dispatchers.IO){
            datastore.saveToken(t)
        }
    }

    fun traerToken() {
        viewModelScope.launch {
            datastore.ObteinToken(object : TokenCallback {
                override fun onTokenReceived(token: String) {
                    // Aquí puedes manejar el token
                    println("Token: $token")
                    this@authViewModel.token.value = token
                }
            })
        }
    }







}

