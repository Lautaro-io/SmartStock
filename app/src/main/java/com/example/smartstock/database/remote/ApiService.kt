package com.example.smartstock.database.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/v0/product/{code}.json")
    suspend fun getProduct(@Path("code") code: String): Response<ProductDataResponse>
}



