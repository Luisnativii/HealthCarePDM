package com.micharlie.healthcare.ui.components.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class authViewModel (application: Application) : AndroidViewModel(application)  {
    private val viewModelContext = getApplication<Application>().applicationContext
    //Data Store
    val datastore = DataStore(viewModelContext)
    fun setToken(t: String){
        viewModelScope.launch (Dispatchers.IO){
            datastore.saveToken(t)
        }
    }

}