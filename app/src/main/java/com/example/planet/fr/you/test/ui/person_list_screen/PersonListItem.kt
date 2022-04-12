package com.example.planet.fr.you.test.ui.person_list_screen

import com.example.planet.fr.you.test.model.Person

data class PersonListItem(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val avatarPath: String
) {
    companion object {
        fun fromPerson(person: Person): PersonListItem {
            return PersonListItem(
                id = person.id,
                firstName = person.firstName,
                lastName = person.lastName,
                avatarPath = person.avatarPath
            )
        }
    }
}
