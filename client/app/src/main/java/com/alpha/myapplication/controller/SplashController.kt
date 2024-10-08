package com.alpha.myapplication.controller

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.stringPreferencesKey
import com.alpha.myapplication.dataStore
import com.alpha.myapplication.models.responses.LoginResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class SplashController(private val context: Context) {
    private suspend fun getToken(): LoginResponse {
        return context.dataStore.data
            .map { settings ->
                LoginResponse(
                    token = settings[stringPreferencesKey("token")].orEmpty(),
                    exp = settings[stringPreferencesKey("exp")].orEmpty(),
                )
            }
            .first()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDateToLocalDeviceTime(dateString: String): ZonedDateTime {

        val zonedDateTime = ZonedDateTime.parse(
            dateString,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME
        )

        val deviceZoneId = ZoneId.systemDefault()

        return zonedDateTime.withZoneSameInstant(deviceZoneId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun isValidToken(): Result<Boolean> {
        return try {

            val token = getToken()

            if (token.exp == "")
                Result.failure<LoginResponse>(Throwable(message = "Invalid Token"))

            val expirationDate = parseDateToLocalDeviceTime(token.exp)
            val currentDate = ZonedDateTime.now()

            if (expirationDate.isBefore(currentDate))
                Result.failure<LoginResponse>(Throwable(message = "Invalid Token"))

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}