package com.example.planet.fr.you.test.ui.person_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planet.fr.you.test.data.Repository
import com.example.planet.fr.you.test.model.Person

class PersonViewModel(private val repository: Repository) : ViewModel() {
    fun loadPerson(personId: Int): LiveData<Person> {
        return repository.getPerson(personId)
    }

    suspend fun deletePerson(personId: Int): Boolean {
        return repository.deletePerson(personId)
    }

    suspend fun updatePerson(person: Person): Boolean {
        return repository.updatePerson(person)
    }
}