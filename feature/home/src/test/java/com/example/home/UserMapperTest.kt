package com.example.home

import com.example.home.data.mapper.UserMapper
import com.example.home.domain.entity.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import user_info.NetworkUser

class UserMapperTest {

    @Test
    fun `mapToDomain maps correctly`() {
        //Given
        val networkUser = NetworkUser(1, "James", "jamesbond007@yopmail.com")

        //When
        val domainUser = UserMapper.mapToDomain(networkUser)

        //Then
        assertEquals(networkUser.id, domainUser.id)
        assertEquals(networkUser.name, domainUser.name)
        assertEquals(networkUser.email, domainUser.email)

    }
}