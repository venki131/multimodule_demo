package com.example.network

import com.example.network.user_info.NetworkUser
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<NetworkUser>
}