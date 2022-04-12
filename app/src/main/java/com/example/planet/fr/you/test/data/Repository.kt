package com.example.planet.fr.you.test.data

import androidx.lifecycle.LiveData
import com.example.planet.fr.you.test.model.Person


interface Repository {
    fun getPersons(): LiveData<List<Person>>
    fun getPerson(personId: Int): LiveData<Person>
    suspend fun savePersons(persons: List<Person>): Boolean
    suspend fun updatePerson(person: Person): Boolean
    suspend fun deletePerson(personId: Int): Boolean
    suspend fun fillDatabaseFromServer()
}