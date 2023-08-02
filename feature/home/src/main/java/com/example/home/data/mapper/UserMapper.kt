package com.example.home.data.mapper

import com.example.home.domain.entity.User
import user_info.NetworkUser

object UserMapper {
    fun mapToDomain(networkUser: NetworkUser): User {
        return User(
            id = networkUser.id,
            name = networkUser.name,
            email = networkUser.email
        )
    }
}
