package com.micharlie.healthcare.ui.components.ViewModel


import android.app.Application
import androidx.datastore.dataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micharlie.healthcare.data.api.ApiService
import com.micharlie.healthcare.data.api.VideoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
}