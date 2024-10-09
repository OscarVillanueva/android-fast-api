package com.alpha.myapplication.factories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alpha.myapplication.viewModel.LoginViewModel

class LoginViewModelFactory(private val dataStore: DataStore<Preferences>): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(dataStore) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}