package com.example.network

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<String>
}