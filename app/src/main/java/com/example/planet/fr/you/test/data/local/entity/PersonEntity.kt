package com.example.planet.fr.you.test.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.planet.fr.you.test.model.Person
import kotlinx.parcelize.Parcelize
import java.net.URL

@Parcelize
@Entity(tableName = "persons")
data class PersonEntity(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val avatarPath: String,
    val email: String
) : Parcelable {
    companion object {
        fun fromPerson(person: Person): PersonEntity {
            return PersonEntity(
                id = person.id,
                firstName = person.firstName,
                lastName = person.lastName,
                avatarPath = person.avatarPath,
                email = person.email
            )
        }
    }

}

fun PersonEntity.toPerson(): Person {
    return Person(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatarPath = avatarPath,
        email = email
    )
}

