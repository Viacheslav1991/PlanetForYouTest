package com.example.planet.fr.you.test.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.planet.fr.you.test.data.local.entity.PersonEntity

@Dao
interface PersonsDao {
    @Query("SELECT * FROM persons")
    fun getAll(): List<PersonEntity>

    @Query("SELECT * FROM persons")
    fun getAllLD(): LiveData<List<PersonEntity>>

    @Query("SELECT * FROM persons WHERE id=:id")
    fun getPersonLD(id: Int): LiveData<PersonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(persons: List<PersonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(PersonEntity: PersonEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(persons: List<PersonEntity>): Int

    @Update
    fun update(PersonEntity: PersonEntity): Int

    @Query("DELETE FROM persons WHERE id=:id")
    fun delete(id: Int): Int
}