package com.alpha.myapplication.controller

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.alpha.myapplication.config.RetrofitInstance
import com.alpha.myapplication.dataStore
import com.alpha.myapplication.models.body.LoginBody
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LoginController(private val context: Context) {

    suspend fun login(username: String, pwd: String): Result<Boolean> {
        return try {
            val body = LoginBody(username, pwd)
            val loginResponse = RetrofitInstance.api.getLoginToken(body)

            context.dataStore.edit { settings ->
                settings[stringPreferencesKey(name = "token")] = loginResponse.token
                settings[stringPreferencesKey(name = "exp")] = loginResponse.exp
            }

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}