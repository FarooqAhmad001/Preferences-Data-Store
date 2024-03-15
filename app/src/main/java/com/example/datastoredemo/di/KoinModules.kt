package com.example.datastoredemo.di

import com.example.datastoredemo.data.local.PreferenceDataStoreHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilModules = module {
single { PreferenceDataStoreHelper(androidContext()) }
}