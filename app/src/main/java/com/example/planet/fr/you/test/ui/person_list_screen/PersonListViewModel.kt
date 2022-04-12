package com.example.planet.fr.you.test.ui.person_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.planet.fr.you.test.data.Repository
import com.example.planet.fr.you.test.model.Person

class PersonListViewModel(repository: Repository) : ViewModel() {
    val persons: LiveData<List<Person>> = repository.getPersons()
}