package com.alpha.myapplication.controller

import com.alpha.myapplication.config.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LoginController {

    suspend fun getHelloWorld(): Result<String> {
        return try {
            val response = RetrofitInstance.api.getHelloWorldResponse()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}