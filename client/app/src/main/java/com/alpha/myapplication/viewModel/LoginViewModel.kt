package com.alpha.myapplication.viewModel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.myapplication.config.RetrofitInstance
import com.alpha.myapplication.models.body.LoginBody
import com.alpha.myapplication.types.LoginStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val dataStore: DataStore<Preferences>): ViewModel() {
    
    private val _loginState = MutableStateFlow(LoginStates.IDLE)
    val loginState: StateFlow<LoginStates> = _loginState

    fun login(username: String, pwd: String) {
        viewModelScope.launch {
            try {
                _loginState.value = LoginStates.LOADING
                
                val body = LoginBody(username, pwd)
                val loginResponse = RetrofitInstance.api.getLoginToken(body)

                dataStore.edit { settings ->
                    settings[stringPreferencesKey(name = "token")] = loginResponse.token
                    settings[stringPreferencesKey(name = "exp")] = loginResponse.exp
                }

                _loginState.value = LoginStates.SUCCESS
            } catch (e: Exception) {
                _loginState.value = LoginStates.FAILURE
            }
        }
    }
}