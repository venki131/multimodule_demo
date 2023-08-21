package com.example.home.data.mapper

import com.example.data.User
import com.example.network.user_info.NetworkUser

object UserMapper {
    fun mapToDomain(networkUser: NetworkUser): com.example.data.User {
        return com.example.data.User(
            id = networkUser.id,
            name = networkUser.name,
            email = networkUser.email
        )
    }
}
