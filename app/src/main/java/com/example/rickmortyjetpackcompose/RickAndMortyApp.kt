package com.example.rickmortyjetpackcompose

import android.app.Application
import com.example.rickmortyjetpackcompose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class RickAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(appModule)
        }
    }
}