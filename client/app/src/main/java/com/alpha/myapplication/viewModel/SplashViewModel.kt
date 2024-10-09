package com.alpha.myapplication.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.myapplication.models.responses.LoginResponse
import com.alpha.myapplication.types.SplashStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class SplashViewModel(private val dataStore: DataStore<Preferences>): ViewModel() {
    private val _splashState = MutableStateFlow(SplashStates.LOADING)
    val splashState: StateFlow<SplashStates> = _splashState

    private suspend fun getToken(): LoginResponse {
        return dataStore.data
            .map { settings ->
                LoginResponse(
                    token = settings[stringPreferencesKey("token")].orEmpty(),
                    exp = settings[stringPreferencesKey("exp")].orEmpty(),
                )
            }
            .first()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseDateToLocalDeviceTime(dateString: String): ZonedDateTime {

        val zonedDateTime = ZonedDateTime.parse(
            dateString,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME
        )

        val deviceZoneId = ZoneId.systemDefault()

        return zonedDateTime.withZoneSameInstant(deviceZoneId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isValidToken() {
        viewModelScope.launch {
            try {
                val token = getToken()

                if (token.exp == "") {
                    _splashState.value = SplashStates.LOGIN
                    return@launch
                }

                val expirationDate = parseDateToLocalDeviceTime(token.exp)
                val currentDate = ZonedDateTime.now()

                if (expirationDate.isBefore(currentDate)) {
                    _splashState.value = SplashStates.LOGIN
                    return@launch
                }

                _splashState.value = SplashStates.HOME

            } catch (e: Exception) {
                _splashState.value = SplashStates.LOGIN
            }
        }
    }
}