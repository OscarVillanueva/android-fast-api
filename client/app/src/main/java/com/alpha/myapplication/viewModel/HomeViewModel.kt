package com.alpha.myapplication.viewModel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.myapplication.types.HomeStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
}