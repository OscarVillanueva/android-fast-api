package com.alpha.myapplication.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alpha.myapplication.viewModel.CreateAccountViewModel

class CreateAccountVMFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateAccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateAccountViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}