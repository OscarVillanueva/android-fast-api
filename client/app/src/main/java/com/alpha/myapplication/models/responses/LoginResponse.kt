package com.alpha.myapplication.models.responses

data class LoginResponse(
    val token: String,
    val exp: String
)