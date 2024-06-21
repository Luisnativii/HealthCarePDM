package com.micharlie.healthcare.ui.components.ViewModel

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.micharlie.healthcare.data.api.TokenCallback
import kotlinx.coroutines.flow.map

val data_store_name = "HC"
val Context.datastore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(name = data_store_name)

class DataStore(private val context: Context) {

    private val token = stringPreferencesKey("token")

    suspend fun saveToken(t: String) {
        context.datastore.edit { dataStore ->
            dataStore[token] = t
        }
    }

    /* viewModel.datastore.getToken().collect {
        response -> variableGlobalDondeGuardeTokenANdroidStudio = token
    }
    */
    suspend fun ObteinToken(callback: TokenCallback) {
        var tokenValue = ""
        context.datastore.data.map { dataStore -> dataStore[token] }.collect {
            tokenValue = it ?: ""
            callback.onTokenReceived(tokenValue)
        }
    }
}
