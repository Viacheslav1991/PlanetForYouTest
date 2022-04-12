package com.example.planet.fr.you.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.planet.fr.you.test.data.local.entity.PersonEntity

@Database(
    entities = [PersonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PersonsDatabase : RoomDatabase() {
    abstract val personsDao: PersonsDao
}