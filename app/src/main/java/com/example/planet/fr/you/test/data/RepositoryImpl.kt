package com.example.planet.fr.you.test.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.planet.fr.you.test.data.local.PersonsDao
import com.example.planet.fr.you.test.data.local.entity.PersonEntity
import com.example.planet.fr.you.test.data.local.entity.toPerson
import com.example.planet.fr.you.test.data.remote.RetrofitService
import com.example.planet.fr.you.test.data.remote.dto.toPerson
import com.example.planet.fr.you.test.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val TAG = "RepositoryImpl"

class RepositoryImpl(
    private val retrofitService: RetrofitService,
    private val personsDao: PersonsDao
) : Repository {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    override fun getPersons(): LiveData<List<Person>> {
        fillDatabaseIfNeeded()
        return Transformations.map(personsDao.getAllLD()) {
            it.map { personEntity -> personEntity.toPerson() }
        }
    }

    private fun fillDatabaseIfNeeded() {
        ioScope.launch {
            if (personsDao.getAll().isEmpty()) {
                fillDatabaseFromServer()
            }
        }
    }

    override suspend fun fillDatabaseFromServer(): Unit = withContext(Dispatchers.IO) {
        val personsFromRemote = retrofitService.getPersons().data?.map { it?.toPerson() }
        personsFromRemote?.forEach { Timber.d("Person from remote: $it", TAG) }
        personsFromRemote?.let {
            personsDao.insertAll(it.map { person ->
                person.let {
                    PersonEntity.fromPerson(
                        it!!
                    )
                }
            })
        }
    }

    override fun getPerson(personId: Int): LiveData<Person> {
        return Transformations.map(personsDao.getPersonLD(personId)) { it.toPerson() }
    }

    override suspend fun savePersons(persons: List<Person>): Boolean = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePerson(person: Person): Boolean = withContext(Dispatchers.IO) {
        return@withContext personsDao.update(PersonEntity.fromPerson(person)) == 1
    }

    override suspend fun deletePerson(personId: Int): Boolean = withContext(Dispatchers.IO) {
        return@withContext personsDao.delete(personId) == 1
    }
}