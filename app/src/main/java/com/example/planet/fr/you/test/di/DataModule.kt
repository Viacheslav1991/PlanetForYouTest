package com.example.planet.fr.you.test.di

import com.example.planet.fr.you.test.data.Repository
import com.example.planet.fr.you.test.data.RepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<Repository> { RepositoryImpl(retrofitService = get(), personsDao = get()) }
}