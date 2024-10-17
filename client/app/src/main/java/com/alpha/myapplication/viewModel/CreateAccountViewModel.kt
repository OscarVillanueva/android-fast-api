package com.alpha.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpha.myapplication.config.RetrofitInstance
import com.alpha.myapplication.models.body.CreateAccountBody
import com.alpha.myapplication.utils.createAccount.ValidationSchema
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CreateAccountViewModel(): ViewModel() {

    private val _validationSchema = MutableStateFlow(ValidationSchema())
    val validationSchema: StateFlow<ValidationSchema> = _validationSchema

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun onUsernameChange(value: String) {
        _validationSchema.value.checkUsername(value)
    }

    fun onPasswordChange(value: String) {
        _validationSchema.value.checkPassword(value)
    }

    fun onConfirmationChange(pwd: String, confirm: String) {
        _validationSchema.value.checkConfirmPassword(pwd = pwd, confirm = confirm)
    }

    fun createAccount(pwd: String, username: String, completion: () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                val body = CreateAccountBody(password = pwd, username = username)

                RetrofitInstance.api.createAccount(body)

                completion()
            }
            catch (e: HttpException) {
                if (e.code() == 409) {
                    _errorMessage.value = "Username already taken"
                }
                else _errorMessage.value = "An error occurred, please try again"
            }
            catch (e: Exception) {
                _errorMessage.value = "An error occurred, please try again"
            }
            finally {
                _isLoading.value = false
            }
        }
    }
}