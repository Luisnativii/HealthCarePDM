package com.micharlie.healthcare.ui.components.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micharlie.healthcare.data.api.ApiService
import com.micharlie.healthcare.data.api.CommentApi
import com.micharlie.healthcare.data.api.NetworkUtils
import com.micharlie.healthcare.data.api.UserApiService
import com.micharlie.healthcare.data.api.VideoApi
import com.micharlie.healthcare.data.api.dataApi
import com.micharlie.healthcare.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


sealed class GetVideoState {
    object Idle : GetVideoState()
    object Loading : GetVideoState()
    data class Success(val videos: List<VideoApi>) : GetVideoState()
    data class CommentsSuccess(val comments: List<CommentApi>) : GetVideoState() // Aquí es donde vaZZZZ
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
                val response =
                    apiService.getVideos().await() // Ejecuta la llamada y obtén una Response

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _getVideoState.value = GetVideoState.Success(body)
                        println("Videos obtenidos: $body") // Imprime los videos obtenidos
                    } else {
                        _getVideoState.value =
                            GetVideoState.Error("El cuerpo de la respuesta es nulo")
                    }
                } else {
                    _getVideoState.value =
                        GetVideoState.Error("Error al obtener los videos: ${response.message()}")
                }
            } catch (e: Exception) {
                _getVideoState.value =
                    GetVideoState.Error("Excepción al obtener los videos: ${e.message}")
                e.printStackTrace() // Imprime la pila de llamadas
            }
        }
    }


    fun getUsersData(token: String) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.GETDATAUSER)
        val service = retrofit.create(UserApiService::class.java)
        val callGetUsers = service.getUsers("Bearer $token")

        callGetUsers.enqueue(object : Callback<List<dataApi>> {
            override fun onResponse(
                call: retrofit2.Call<List<dataApi>>,
                response: Response<List<dataApi>>
            ) {
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

    suspend fun getUsersDataWithResult(token: String): Boolean {
        return try {
            getUsersData(token)
            true
        } catch (e: Exception) {
            false
        }
    }


    fun updateHeight(token: String, newHeight: Int) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
        val service = retrofit.create(UserApiService::class.java)
        val call = service.updateHeight("Bearer $token", mapOf("_height" to newHeight))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Height updated successfully")
                } else {
                    println("Failed to update height: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to update height: ${t.message}")
            }
        })
    }


    fun updateWeight(token: String, newWeight: Int) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
        val service = retrofit.create(UserApiService::class.java)
        val call = service.updateWeight("Bearer $token", mapOf("_weight" to newWeight))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Weight updated successfully")
                } else {
                    println("Failed to update weight: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to update weight: ${t.message}")
            }
        })
    }

    fun updateMuscularMass(token: String, newMuscularMass: Int) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
        val service = retrofit.create(UserApiService::class.java)
        val call =
            service.updateMuscularMass("Bearer $token", mapOf("_muscularMass" to newMuscularMass))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Muscular mass updated successfully")
                } else {
                    println("Failed to update muscular mass: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to update muscular mass: ${t.message}")
            }
        })
    }


    fun updateBodyFat(token: String, newBodyFat: Float) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
        val service = retrofit.create(UserApiService::class.java)
        val call = service.updateBodyFat("Bearer $token", mapOf("_bodyFat" to newBodyFat))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Body fat updated successfully")
                } else {
                    println("Failed to update body fat: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to update body fat: ${t.message}")
            }
        })
    }


    fun updateCholesterol(token: String, newCholesterol: Int) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
        val service = retrofit.create(UserApiService::class.java)
        val call =
            service.updateCholesterol("Bearer $token", mapOf("_cholesterol" to newCholesterol))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Cholesterol updated successfully")
                } else {
                    println("Failed to update cholesterol: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to update cholesterol: ${t.message}")
            }
        })
    }

    fun updateBloodGlucose(token: String, newBloodGlucose: Float) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
        val service = retrofit.create(UserApiService::class.java)
        val call =
            service.updateBloodGlucose("Bearer $token", mapOf("_bloodGlucose" to newBloodGlucose))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Blood glucose updated successfully")
                } else {
                    println("Failed to update blood glucose: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to update blood glucose: ${t.message}")
            }
        })
    }

    fun updateBloodPressure(token: String, bloodPressure: String) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
        val service = retrofit.create(UserApiService::class.java)
        val call = service.updateBloodPressure("Bearer $token", mapOf("_bloodPressure" to bloodPressure))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Blood pressure updated successfully")
                } else {
                    println("Failed to update blood pressure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to update blood pressure: ${t.message}")
            }
        })
    }

    fun postComment(token: String, content: String) {
        val retrofit = NetworkUtils.getRetrofitInstance(Constants.BASE_URL)
        val service = retrofit.create(UserApiService::class.java)

        val call = service.postComment("Bearer $token", mapOf("content" to content))

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Comment posted successfully")
                } else {
                    println("Failed to post comment: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Failed to post comment: ${t.message}")
            }
        })
    }

    private val _comments = MutableStateFlow<List<CommentApi>>(emptyList())
    val comments: StateFlow<List<CommentApi>> = _comments

    fun getComments() {
        viewModelScope.launch(Dispatchers.IO) {
            _getVideoState.value = GetVideoState.Loading
            try {
                val response = apiService.getComments().await()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _getVideoState.value = GetVideoState.CommentsSuccess(body)
                        println("Comentarios obtenidos: $body")
                        _comments.value = body // Actualiza la lista de comentarios
                    } else {
                        _getVideoState.value = GetVideoState.Error("El cuerpo de la respuesta es nulo")
                    }
                } else {
                    _getVideoState.value = GetVideoState.Error("Error al obtener los comentarios: ${response.message()}")
                }
            } catch (e: Exception) {
                _getVideoState.value = GetVideoState.Error("Excepción al obtener los comentarios: ${e.message}")
                e.printStackTrace()
            }
        }
    }








}
