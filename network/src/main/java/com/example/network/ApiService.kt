package com.example.network

import retrofit2.http.GET
import com.example.network.user_info.NetworkUser

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<NetworkUser>
}