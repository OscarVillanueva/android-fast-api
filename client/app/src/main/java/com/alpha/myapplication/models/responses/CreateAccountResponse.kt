package com.alpha.myapplication.models.responses

data class CreateAccountResponse (
    val id: Int,
    val username: String,
    val message: String?
)