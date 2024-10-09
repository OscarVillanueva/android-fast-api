package com.alpha.myapplication.controller

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.alpha.myapplication.config.RetrofitInstance
import com.alpha.myapplication.models.body.LoginBody

class LoginController(private val dataStore: DataStore<Preferences>) {
    suspend fun login(username: String, pwd: String): Result<Boolean> {
        return try {
            val body = LoginBody(username, pwd)
            val loginResponse = RetrofitInstance.api.getLoginToken(body)

            dataStore.edit { settings ->
                settings[stringPreferencesKey(name = "token")] = loginResponse.token
                settings[stringPreferencesKey(name = "exp")] = loginResponse.exp
            }

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}