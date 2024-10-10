package com.alpha.myapplication.viewModel

import android.util.Log
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
import retrofit2.HttpException
import java.net.HttpRetryException

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

                Log.d("Login", "Pase")

                _loginState.value = LoginStates.SUCCESS
            }
            catch (e: HttpException) {
                if (e.code() == 422) {
                    _loginState.value = LoginStates.INVALID_CREDENTIALS
                }
                else _loginState.value = LoginStates.FAILURE
            }
            catch (e: Exception) {
                Log.d("Login", "falle")
                _loginState.value = LoginStates.FAILURE
            }
        }
    }
}