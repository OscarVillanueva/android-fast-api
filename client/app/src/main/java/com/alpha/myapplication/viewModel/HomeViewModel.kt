package com.alpha.myapplication.viewModel

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.myapplication.config.RetrofitInstance
import com.alpha.myapplication.models.body.CreateTodo
import com.alpha.myapplication.types.HomeStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val dataStore: DataStore<Preferences>): ViewModel() {

    private val _homeState = MutableStateFlow(HomeStates.IDLE)
    val homeState: StateFlow<HomeStates> = _homeState

    fun logOut() {
        viewModelScope.launch {
            dataStore.edit { settings ->
                settings.clear()
                _homeState.value = HomeStates.LOG_OUT
            }
        }
    }

    fun addTodo(todo: String) {
        viewModelScope.launch {
            try {
                val token = getToken()

                val response = RetrofitInstance.api.createTodo(
                    token = "Bearer $token",
                    todo = CreateTodo(todo = todo)
                )

                Log.d("HomeViewModel", "id: ${response.id} -> todo: ${response.todo}")
            }
            catch (e: Exception) {
                Log.d("HomeViewModel", "error $e")
            }
        }
    }

    private suspend fun getToken(): String {
        return dataStore.data
            .map { settings ->
                settings[stringPreferencesKey("token")].orEmpty()
            }
            .first()
    }
}