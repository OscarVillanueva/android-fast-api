package com.alpha.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.alpha.myapplication.utils.createAccount.ValidationSchema
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateAccountViewModel(): ViewModel() {

    private val _validationSchema = MutableStateFlow(ValidationSchema())
    val validationSchema: StateFlow<ValidationSchema> = _validationSchema

    fun onUsernameChange(value: String) {
        _validationSchema.value.checkUsername(value)
    }

    fun onPasswordChange(value: String) {
        _validationSchema.value.checkPassword(value)
    }

    // onConfirmationChange
    fun onConfirmationChange(pwd: String, confirm: String) {
        _validationSchema.value.checkConfirmPassword(pwd = pwd, confirm = confirm)
    }

    // createAccount
}