package com.micharlie.healthcare.ui.components.ViewModel


import android.app.Application
import android.view.WindowInsetsAnimation
import androidx.datastore.dataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micharlie.healthcare.data.api.ApiService
import com.micharlie.healthcare.data.api.NetworkUtils
import com.micharlie.healthcare.data.api.UserApiService
import com.micharlie.healthcare.data.api.VideoApi
import com.micharlie.healthcare.data.api.dataApi
import com.micharlie.healthcare.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

sealed class GetVideoState {
    object Idle : GetVideoState()
    object Loading : GetVideoState()
    data class Success(val videos: List<VideoApi>) : GetVideoState()
    data class Error(val message: String) : GetVideoState()
}

class GetVideoViewModel(private val apiService: ApiService) : ViewModel() {
    private val _getVideoState = MutableStateFlow<GetVideoState>(GetVideoState.Idle)
    val getVideoState: StateFlow<GetVideoState> = _getVideoState

    val userData = MutableLiveData<List<dataApi>>()

    fun getVideos() {
        viewModelScope.launch(Dispatchers.IO) {
            _getVideoState.value = GetVideoState.Loading
            try {
                val response = apiService.getVideos().await() // Ejecuta la llamada y obtén una Response

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _getVideoState.value = GetVideoState.Success(body)
                        println("Videos obtenidos: $body") // Imprime los videos obtenidos
                    } else {
                        _getVideoState.value = GetVideoState.Error("El cuerpo de la respuesta es nulo")
                    }
                } else {
                    _getVideoState.value = GetVideoState.Error("Error al obtener los videos: ${response.message()}")
                }
            } catch (e: Exception) {
                _getVideoState.value = GetVideoState.Error("Excepción al obtener los videos: ${e.message}")
                e.printStackTrace() // Imprime la pila de llamadas
            }
        }
    }



    fun getUsersData(token: String) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.GETDATAUSER)
        val service = retrofit.create(UserApiService::class.java)
        val callGetUsers = service.getUsers("Bearer $token")

        callGetUsers.enqueue(object : Callback<List<dataApi>> {
            override fun onResponse(call: retrofit2.Call<List<dataApi>>, response: Response<List<dataApi>>) {
                if (response.isSuccessful) {
                    userData.value = response.body()
                    println("Get users successful: ${response.body()}")
                } else {
                    println("Get users failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<List<dataApi>>, t: Throwable) {
                println("Get users failed: ${t.message}")
            }
        })
    }
}