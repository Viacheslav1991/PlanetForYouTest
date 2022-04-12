package com.example.planet.fr.you.test.di

import android.content.Context
import androidx.room.Room
import com.example.planet.fr.you.test.data.local.PersonsDao
import com.example.planet.fr.you.test.data.local.PersonsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val PERSONS_DATABASE_NAME = "Persons.db"

val roomModule = module {
    fun providePersonsDatabase(context: Context): PersonsDatabase {
        return Room.databaseBuilder(context, PersonsDatabase::class.java, PERSONS_DATABASE_NAME)
            .fallbackToDestructiveMigration()//dangerous thing!!!
            .build()
    }

    fun providePersonsDao(database: PersonsDatabase): PersonsDao {
        return database.personsDao
    }

    single { providePersonsDatabase(context = androidContext()) }
    single { providePersonsDao(database = get()) }
}