package com.example.planet.fr.you.test.ui.person_list_screen

sealed class PersonListEvent(val personId: Int) {
    class OnClick(personId: Int) : PersonListEvent(personId)
}
