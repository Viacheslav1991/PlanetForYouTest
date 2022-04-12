package com.example.planet.fr.you.test

import android.app.Application
import calm.sleep.relaxing.persons.noise.di.appModule
import com.example.planet.fr.you.test.di.dataModule
import com.example.planet.fr.you.test.di.retrofitModule
import com.example.planet.fr.you.test.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, roomModule, retrofitModule))
        }
    }
}