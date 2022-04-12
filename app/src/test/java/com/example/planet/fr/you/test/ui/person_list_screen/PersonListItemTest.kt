package com.example.planet.fr.you.test.ui.person_list_screen

import com.example.planet.fr.you.test.model.Person
import org.junit.Test
import kotlin.test.assertEquals

class PersonListItemTest {

    @Test
    fun test_Person_to_PersonItemList_mapper() {
        val person = Person(
            id = 2,
            firstName = "Tolia",
            lastName = "Averin",
            avatarPath = "path2",
            email = "email"
        )
        val personListItem = PersonListItem.fromPerson(person)

        assertEquals(person.id, personListItem.id)
        assertEquals(person.firstName, personListItem.firstName)
        assertEquals(person.lastName, personListItem.lastName)
        assertEquals(person.avatarPath, personListItem.avatarPath)
    }

}