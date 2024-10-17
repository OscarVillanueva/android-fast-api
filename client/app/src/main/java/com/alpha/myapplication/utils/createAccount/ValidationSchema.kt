package com.alpha.myapplication.utils.createAccount

class ValidationSchema {

    var isUsernameValid: Boolean = false
        private set

    var isPasswordValid: Boolean = false
        private set

    var isConfirmationValid: Boolean = false
        private set

    fun checkUsername(username: String) {
        if (username.trim().isEmpty() || username.trim().length < 5) {
            isUsernameValid = false
            return
        }

        isUsernameValid = true
    }


}