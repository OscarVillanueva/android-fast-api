package com.alpha.myapplication.services

import retrofit2.http.GET

interface AlphaAPIService {

    @GET("/")
    suspend fun getHelloWorldResponse(): String

}