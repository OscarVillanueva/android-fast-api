package com.alpha.myapplication.factories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alpha.myapplication.viewModel.HomeViewModel

class HomeViewModelFactory(private val dataStore: DataStore<Preferences>): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dataStore) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}