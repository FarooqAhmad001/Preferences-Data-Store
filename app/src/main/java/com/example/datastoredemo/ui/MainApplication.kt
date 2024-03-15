package com.example.datastoredemo.ui

import android.app.Application
import com.example.datastoredemo.di.utilModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(utilModules)
        }
    }

}