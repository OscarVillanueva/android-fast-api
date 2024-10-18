package com.alpha.myapplication.services

import com.alpha.myapplication.models.body.CreateAccountBody
import com.alpha.myapplication.models.body.CreateTodo
import com.alpha.myapplication.models.body.LoginBody
import com.alpha.myapplication.models.responses.AddTodoResponse
import com.alpha.myapplication.models.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AlphaAPIService {
    @GET("/")
    suspend fun getHelloWorldResponse(): String

    @POST("/login")
    suspend fun getLoginToken(@Body loginBody: LoginBody): LoginResponse

    @POST("/create-user")
    suspend fun createAccount(@Body createAccountBody: CreateAccountBody)

    @POST("/todo")
    suspend fun createTodo(
        @Body todo: CreateTodo,
        @Header("Authorization") token: String,
    ): AddTodoResponse
}