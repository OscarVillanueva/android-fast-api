package com.alpha.myapplication.utils.createAccount


class ValidationSchema {

    var isUsernameValid = Pair(false, "not touched")
        private set

    var isPasswordValid = Pair(false, "not touched")
        private set

    var isConfirmationValid = Pair(false, "not touched")
        private set

    var isValidAccount: Boolean = false
        private set
        get() = isUsernameValid.first && isPasswordValid.first && isConfirmationValid.first

    fun checkUsername(username: String) {
        isUsernameValid = when {
            username.trim().isEmpty() ->  Pair(false, "The username is required")
            username.trim().length < 5 -> Pair(false, "The username is too short")
            else -> Pair(true, "")
        }
    }

    fun checkPassword(pwd: String) {
        isPasswordValid = when {
            pwd.trim().isEmpty() ->  Pair(false, "The password is required")
            pwd.trim().length < 8 -> Pair(false, "The password is too short")
            else -> Pair(true, "")
        }
    }

    fun checkConfirmPassword(pwd: String, confirm: String) {
        isConfirmationValid = when {
            pwd == confirm -> Pair(true, "")
            else -> Pair(false, "Passwords must match")
        }
    }

}