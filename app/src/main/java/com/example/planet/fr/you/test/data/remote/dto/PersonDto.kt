package com.example.planet.fr.you.test.data.remote.dto

import com.example.planet.fr.you.test.model.Person
import com.google.gson.annotations.SerializedName

data class PersonDto(
    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("avatar")
    val avatar: String,

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("email")
    val email: String
)

fun PersonDto.toPerson(): Person {
    return Person(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatarPath = avatar,
        email = email
    )
}